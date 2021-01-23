package br.com.infox.treinamento.trainee.filters;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class RedirectLandingPageFilter implements Filter {
	
	private static final Logger LOG = Logger.getLogger("br.com.infox.treinamento.trainee.filters");
	private String initParameter;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("INICIANDO FILTRO RedirectLandingPageFilter COM OS PARAMETROS");
		for (String nomeParametro : Collections.list(filterConfig.getInitParameterNames())) {
			LOG.info(String.format("	'%s' => %s", nomeParametro, filterConfig.getInitParameter(nomeParametro)));
		}
		
		this.initParameter = filterConfig.getInitParameter("landingPage");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOG.info("Iniciando método doFilter do RedirectLandingPageFilter");
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(initParameter);
		LOG.info("Finalizando método doFilter do RedirectLandingPageFilter");
	}

	@Override
	public void destroy() {
		LOG.info("ENCERRANDO FILTRO RedirectLandingPageFilter");
	}

}
