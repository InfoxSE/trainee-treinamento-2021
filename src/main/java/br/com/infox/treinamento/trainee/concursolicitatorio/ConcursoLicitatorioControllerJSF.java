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
	
	private ConcursoLicitatorio novoConcurso;
	
	private List<ConcursoLicitatorio> concursos;
	
	@PostConstruct
	public void init() {
		this.novoConcurso = new ConcursoLicitatorio();
	}
	
	@PreDestroy
	public void destroy() {
		
	}
	
	public void registrar() {
		if (concursos == null) {
			this.concursos = new ArrayList<>(0);
		}
		getConcursos().add(getNovoConcurso());
		this.novoConcurso = new ConcursoLicitatorio();
	}
	
	public ConcursoLicitatorio getNovoConcurso() {
		return novoConcurso;
	}
	
	public List<ConcursoLicitatorio> getConcursos() {
		return concursos;
	}
	
}
