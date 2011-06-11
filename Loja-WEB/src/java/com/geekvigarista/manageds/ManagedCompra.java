package com.geekvigarista.manageds;

import com.geekvigarista.pojo.Compra;
import com.geekvigarista.services.CompraServiceLocal;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
    
    @EJB
    private CompraServiceLocal servico;
    
    private Compra compra = new Compra();

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    public ManagedCompra() {
    }

    public String onFlowProcess(FlowEvent event) {
        logger.info("Passo de compra atual:" + event.getOldStep());
        return event.getNewStep();
    }
}
