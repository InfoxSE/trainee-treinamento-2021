package br.com.infox.treinamento.trainee.cadastroconcurso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ConcursoControllerJSF implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger("trainee.member");
	
	private Concurso novoConcurso;
	private static List<Concurso> concursos;
	
	@PostConstruct
	public void init() {
		LOG.info("PostConstruct ConcursoControllerJSF");
		this.novoConcurso = new Concurso();
	}
	
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy ConcursoControllerJSF");
	}
	
	public void registrar() {
		if (concursos == null) {
			this.concursos = new ArrayList<>(0);
		}
		
		//Validando data limite igual ou inferior a disponibilizacao
		int dataComparada = validarDatas();
		if (dataComparada <= 0) {
			LOG.info("ENTRANDO NA CONDIÇÃO DE ERRO NA DATA LIMITE DO CONCURSO");
			FacesContext.getCurrentInstance().addMessage("data",
					new FacesMessage("A data limite não pode ser IGUAL ou ANTERIOR a data de disponibilização"));
			return;
		}
		
		
		getConcursos().add(getNovoConcurso());
		this.novoConcurso = new Concurso();
		LOG.info("CONCURSOS DA LISTA");
		for (Concurso concurso : concursos) {
			LOG.info("NOME => " + concurso.getNome());
			LOG.info("DESCRIÇÃO => " + concurso.getDescricao());
			LOG.info("DATA DE DISPONIBILIZACAO => " + concurso.getDataDisponibilizacao());
			LOG.info("DATA LIMITE => " + concurso.getDataLimite());
			LOG.info("QUANTIDADE => " + concurso.getQuantidade());
		}
	}

	private Integer validarDatas() {
		Date dataLimite = novoConcurso.getDataLimite().getTime();
		Date dataDisponibilizacao = novoConcurso.getDataDisponibilizacao().getTime();
		
		//O método compareTo() retorna 0 se as datas forem iguais, 
		//e um valor menor que 0 se a data for anterior a data do argumento
		int dataComparada = dataLimite.compareTo(dataDisponibilizacao);
		
		return dataComparada;
	}

	public Concurso getNovoConcurso() {
		return novoConcurso;
	}

	public List<Concurso> getConcursos() {
		return concursos;
	}
	
	

}
