package br.com.infox.treinamento.trainee.filters;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omnifaces.filter.HttpFilter;

@WebFilter("*")
public class ResquestLoggingFilter extends HttpFilter {

	private static final Logger LOG = Logger.getLogger("br.com.infox.treinamento.trainee.filters");
	private String initParameter;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		LOG.info("INICIANDO FILTRO ResquestLoggingFilter COM OS PARAMETROS");
		for (String nomeParametro : Collections.list(filterConfig.getInitParameterNames())) {
			LOG.info(String.format("	'%s' => %s", nomeParametro, filterConfig.getInitParameter(nomeParametro)));
		}
		
		this.initParameter = filterConfig.getInitParameter("landingPage");
	}
	
	@Override
	public void destroy() {
		LOG.info("ENCERRANDO FILTRO ResquestLoggingFilter");
		super.destroy();
	}
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			FilterChain chain) throws ServletException, IOException {
		LOG.info(request.getRequestURI() + " :: Iniciando método doFilter do RedirectLandingPageFilter");
		long start = System.currentTimeMillis();
		
		for (String nomeParametro : Collections.list(request.getParameterNames())) {
			String value = request.getParameter(nomeParametro);
			LOG.info(request.getRequestURI() + " :: REQUEST PARAMS :: " + nomeParametro + " :: " + value);
		}
		chain.doFilter(request, response);		
		
		long end = System.currentTimeMillis();
		float duration = (end - start) / 1000.0f;
		LOG.info(request.getRequestURI() + " :: Finalizando método doFilter do RedirectLandingPageFilter após " + duration+"s");

	}

}
