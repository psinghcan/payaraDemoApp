package com.exitrealty.payaraDemoApp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.transaction.Transactional;

import com.exitrealty.payaraDemoApp.domain.DummyEntity;

@Named
public class DummyService extends BaseService<DummyEntity> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public DummyService(){
        super(DummyEntity.class);
    }
    
    @Transactional
    public List<DummyEntity> findAllDummyEntities() {
        
        return entityManager.createQuery("SELECT o FROM Dummy o ", DummyEntity.class).getResultList();
    }
    
    @Override
    @Transactional
    public long countAllEntries() {
        return entityManager.createQuery("SELECT COUNT(o) FROM Dummy o", Long.class).getSingleResult();
    }
    
    @Override
    protected void handleDependenciesBeforeDelete(DummyEntity dummy) {

        /* This is called before a Dummy is deleted. Place here all the
           steps to cut dependencies to other entities */
        
    }

}
