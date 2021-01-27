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


public class JpaEntityLogger {

	private static final Logger LOG = Logger.getLogger("jpa.listener.entity");

    @PostLoad
    public void postLoad(Object entity) {
    	LOG.info(getClass().getSimpleName()+".postLoad()");
    }

	@PrePersist
    public void prePersist(Object entity) {
		LOG.info(getClass().getSimpleName()+".prePersist()");
    }

    @PostPersist
    public void postPersist(Object entity) {
    	EntityLogRegistryBean.instance().registrarLog(TipoOperacaoLogEnum.I, entity);
    	LOG.info(getClass().getSimpleName()+".postPersist()");
    }

    @PreUpdate
    public void preUpdate(Object entity) {
    	LOG.info(getClass().getSimpleName()+".preUpdate()");
    }

    @PostUpdate
    public void postUpdate(Object entity) {
    	EntityLogRegistryBean.instance().registrarLog(TipoOperacaoLogEnum.U, entity);
    	LOG.info(getClass().getSimpleName()+".postUpdate()");
    }

    @PreRemove
    public void preRemove(Object entity) {
    	LOG.info(getClass().getSimpleName()+".preRemove()");
    }

    @PostRemove
    public void postRemove(Object entity) {
    	EntityLogRegistryBean.instance().registrarLog(TipoOperacaoLogEnum.D, entity);
    	LOG.info(getClass().getSimpleName()+".postRemove()");
    }


}
