package br.com.infox.treinamento.trainee.cadastroconcurso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
		getConcursos().add(getNovoConcurso());
		this.novoConcurso = new Concurso();
		LOG.info("CONCURSOS DA LISTA");
		for (Concurso concurso : concursos) {
			LOG.info("NOME => " + concurso.getNome());
			LOG.info("DESCRIÇÃO => " + concurso.getDescricao());
			LOG.info("DATA DE DISPONIBILIZACAO => " + concurso.getDescricao());
			LOG.info("DATA LIMITE => " + concurso.getDataDisponibilizacao());
			LOG.info("QUANTIDADE => " + concurso.getDataLimite());
		}
	}

	public Concurso getNovoConcurso() {
		return novoConcurso;
	}

	public List<Concurso> getConcursos() {
		return concursos;
	}
	
	

}
