package br.com.infox.treinamento.trainee.ws.client;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class PessoaFisicaEndpointServerClient {

	private static int parseInt(Character c) {
        return Integer.parseInt(c.toString());
    }

	public static void main(String[] args) throws DatatypeConfigurationException {
			PessoaFisicaEndpointServerService pessoaFisicaEndpointServerService = new PessoaFisicaEndpointServerService();
			PessoaFisicaEndpointServer port = pessoaFisicaEndpointServerService.getPessoaFisicaEndpointServerPort();
			String resultado = port.sayHello("Teste");
			System.out.println(resultado);
			PessoaFisica pf = new PessoaFisica();
			pf.setCpf(generateCpf());
			pf.setName("Nome um");
			ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of("UTC"));
			GregorianCalendar c = GregorianCalendar.from(zonedDateTime);
		    XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			pf.setBirthDate(date2);
			port.cadastrarPessoaFisica(pf);
	}

	private static String generateCpf() {
		Random r = new Random();
		int nextInt = r.nextInt(999_999_999);
		String cpf = String.format("%1$9s", nextInt).replace(' ', '0');
		int soma = 0;
		for (int i = 0; i < 9; i++) {
		    soma = soma + parseInt(cpf.charAt(i)) * (10 - i);
		}
		int dv1 = 11 - (soma % 11);
		if (dv1 == 10 || dv1 == 11) {
		    dv1 = 0;
		}

		soma = 0;
		for (int i = 0; i < 9; i++) {
		    soma = soma + parseInt(cpf.charAt(i)) * (11 - i);
		}
		soma = soma + dv1 * 2;
		int dv2 = 11 - (soma % 11);
		if (dv2 == 10 || dv2 == 11) {
		    dv2 = 0;
		}
		String res = cpf+dv1+dv2;
		return res;
	}
}
