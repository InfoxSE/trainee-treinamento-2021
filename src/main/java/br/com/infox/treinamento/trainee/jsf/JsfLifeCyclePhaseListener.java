package br.com.infox.treinamento.trainee.jsf;

import java.text.MessageFormat;
import java.util.logging.Logger;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class JsfLifeCyclePhaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.filters");

    private volatile long startPhaseTime;

	@Override
	public void beforePhase(PhaseEvent event) {
		PhaseId phaseId = event.getPhaseId();
        long currentTimeMillis = System.currentTimeMillis();
        this.startPhaseTime = currentTimeMillis;
        LOG.info(MessageFormat.format("Start ''{0}''", phaseId));
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		PhaseId phaseId = event.getPhaseId();
        long currentTimeMillis = System.currentTimeMillis();
        long elapsedTime = currentTimeMillis - this.startPhaseTime;
        LOG.info(MessageFormat.format("End ''{0}'' [{1} ms]", phaseId, elapsedTime));

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
