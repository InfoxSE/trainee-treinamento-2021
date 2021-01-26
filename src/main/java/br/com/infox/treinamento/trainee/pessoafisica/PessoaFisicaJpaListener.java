package br.com.infox.treinamento.trainee.pessoafisica;

import java.util.logging.Logger;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import br.com.infox.treinamento.trainee.jpa.EntityLogRegistryBean;
import br.com.infox.treinamento.trainee.jpa.TipoOperacaoLogEnum;


public class PessoaFisicaJpaListener {

	private static final Logger LOG = Logger.getLogger("jpa.listener.PessoaFisica");

    @PostLoad
    public void postLoad(PessoaFisica pessoaFisica) {
    	LOG.info(getClass().getSimpleName()+".postLoad()");
    }

	@PrePersist
    public void prePersist(PessoaFisica pessoaFisica) {
		LOG.info(getClass().getSimpleName()+".prePersist()");
    }

    @PostPersist
    public void postPersist(PessoaFisica pessoaFisica) {
    	EntityLogRegistryBean.instance().registrarLog(TipoOperacaoLogEnum.I, pessoaFisica);
    	LOG.info(getClass().getSimpleName()+".postPersist()");
    }

    @PreUpdate
    public void preUpdate(PessoaFisica pessoaFisica) {
    	LOG.info(getClass().getSimpleName()+".preUpdate()");
    }

    @PostUpdate
    public void postUpdate(PessoaFisica pessoaFisica) {
    	EntityLogRegistryBean.instance().registrarLog(TipoOperacaoLogEnum.U, pessoaFisica);
    	LOG.info(getClass().getSimpleName()+".postUpdate()");
    }

    @PreRemove
    public void preRemove(PessoaFisica pessoaFisica) {
    	LOG.info(getClass().getSimpleName()+".preRemove()");
    }

    @PostRemove
    public void postRemove(PessoaFisica pessoaFisica) {
    	EntityLogRegistryBean.instance().registrarLog(TipoOperacaoLogEnum.D, pessoaFisica);
    	LOG.info(getClass().getSimpleName()+".postRemove()");
    }


}
