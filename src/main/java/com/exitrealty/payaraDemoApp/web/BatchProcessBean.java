package com.exitrealty.payaraDemoApp.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named("batchProcess")
@RequestScoped

public class BatchProcessBean implements Serializable {

    public String process(){
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO","process is done"));
        return "";
    }

    public String longProcess(){
        System.out.println("inside the process");
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "process is started: Please wait","process is started: Please wait"));
        for (int i=0; i< 1000; i++){
            try {
                Thread.currentThread().wait(1000);
            } catch(Exception e){}
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "process is finished: Well done","process is finished: Well done"));
        return "";
    }

    private String message = "";
    private String field1 = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String doSomeAction(){
        if(this.message.equals("")){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Message","Error Message"));
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal Message","Fatal Message"));
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "WARN Message","WARN Message"));
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO Message","INFO Message"));
        }
        return "";
    }

    private  int number;

    public void increment(){
        number++;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
