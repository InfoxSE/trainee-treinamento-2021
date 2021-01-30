package br.com.infox.treinamento.trainee.concursolicitatorio;

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
public class ConcursoLicitatorioControllerJSF implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.concursocontroller");
	
	private ConcursoLicitatorio novoConcurso;
	
	private List<ConcursoLicitatorio> concursos;
	
	@PostConstruct
	public void init() {
		LOG.info("PostConstruct ConcursoLicitatorioControllerJSF");
		this.novoConcurso = new ConcursoLicitatorio();
	}
	
	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy ConcursoLicitatorioControllerJSF");
	}
	
	public void registrar() {
		if (concursos == null) {
			this.concursos = new ArrayList<>(0);
		}
		getConcursos().add(getNovoConcurso());
		this.novoConcurso = new ConcursoLicitatorio();
		LOG.info("CONCURSOS DA LISTA");
		for (ConcursoLicitatorio concurso : concursos) {
			LOG.info("NOME => " + concurso.getNome());
			LOG.info("DESCRIÇÃO => " + concurso.getDescricao());
			LOG.info("QUANTIDADE => " + concurso.getQuantidade());
			LOG.info("DATA DE DISPONIBILIZAÇÃO => " + concurso.getDataDisponibilizacao());
			LOG.info("DATA LIMITE => " + concurso.getDataLimite());
		}
	}
	
	public ConcursoLicitatorio getNovoConcurso() {
		return novoConcurso;
	}
	
	public List<ConcursoLicitatorio> getConcursos() {
		return concursos;
	}
	
}
