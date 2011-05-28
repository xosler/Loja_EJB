package com.geekvigarista.manageds;

import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author carlos
 */
@ManagedBean
@SessionScoped
public class ManagedCompra {

    private static Logger logger = Logger.getLogger(ManagedCompra.class.getName());

    public ManagedCompra() {
    }

    public String onFlowProcess(FlowEvent event) {
        logger.info("Passo de compra atual:" + event.getOldStep());
        return event.getNewStep();
    }
}
