package com.exitrealty.payaraDemoApp.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import com.exitrealty.payaraDemoApp.domain.DummyEntity;
import com.exitrealty.payaraDemoApp.service.DummyService;
import com.exitrealty.payaraDemoApp.web.util.MessageFactory;

@Named("dummyBean")
@ViewScoped
public class DummyBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(DummyBean.class.getName());
    
    private List<DummyEntity> dummyList;

    private DummyEntity dummy;
    
    @Inject
    private DummyService dummyService;
    
    public void prepareNewDummy() {
        reset();
        this.dummy = new DummyEntity();
        // set any default values now, if you need
        // Example: this.dummy.setAnything("test");
    }

    public String persist() {

        String message;
        
        try {
            
            if (dummy.getId() != null) {
                dummy = dummyService.update(dummy);
                message = "message_successfully_updated";
            } else {
                dummy = dummyService.save(dummy);
                message = "message_successfully_created";
            }
        } catch (OptimisticLockException e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_optimistic_locking_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_save_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        }
        
        dummyList = null;

        FacesMessage facesMessage = MessageFactory.getMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        
        return null;
    }
    
    public String delete() {
        
        String message;
        
        try {
            dummyService.delete(dummy);
            message = "message_successfully_deleted";
            reset();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "message_delete_exception";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        }
        FacesContext.getCurrentInstance().addMessage(null, MessageFactory.getMessage(message));
        
        return null;
    }
    
    public void reset() {
        dummy = null;
        dummyList = null;
        
    }

    public DummyEntity getDummy() {
        // Need to check for null, because some strange behaviour of f:viewParam
                // Sometimes it is setting to null
        if (this.dummy == null) {
            prepareNewDummy();
        }
        return this.dummy;
    }
    
    public void setDummy(DummyEntity dummy) {
            if (dummy != null) {
        this.dummy = dummy;
            }
    }
    
    public List<DummyEntity> getDummyList() {
        if (dummyList == null) {
            dummyList = dummyService.findAllDummyEntities();
        }
        return dummyList;
    }

    public void setDummyList(List<DummyEntity> dummyList) {
        this.dummyList = dummyList;
    }
    
}
