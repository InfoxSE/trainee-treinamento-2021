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
public class RequestLoggingFilter extends HttpFilter {

	private static final Logger LOG = Logger.getLogger("br.com.infox.treinamento.trainee.filters");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		LOG.info("INICIANDO FILTRO RequestLoggingFilter COM OS PARÂMETROS");
		for (String nomeParametro : Collections.list(filterConfig.getInitParameterNames())) {
			LOG.info(String.format("    '%s' => %s", nomeParametro, filterConfig.getInitParameter(nomeParametro)));
		}
	}
	@Override
	public void destroy() {
		LOG.info("ENCERRANDO FILTRO RequestLoggingFilter");
		super.destroy();
	}

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			FilterChain chain) throws ServletException, IOException {
		LOG.info(request.getRequestURI() + " :: Iniciando método doFilter do RequestLoggingFilter");
		long start = System.currentTimeMillis();
		for (String nome : Collections.list(request.getParameterNames())) {
			String value = request.getParameter(nome);
			LOG.info(request.getRequestURI() + " :: REQUEST PARAMS :: "+ nome + " :: "+value);
		}

		chain.doFilter(request, response);

		long end = System.currentTimeMillis();
		float duration = (end - start) / 1000.0f;
		LOG.info(request.getRequestURI() + " :: Finalizando método doFilter do RequestLoggingFilter após "+duration+"s");
	}

}
