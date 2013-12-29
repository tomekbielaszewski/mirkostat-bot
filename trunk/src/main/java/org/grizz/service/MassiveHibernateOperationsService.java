package org.grizz.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.collections.CollectionUtils;
import org.grizz.model.Identifable;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MassiveHibernateOperationsService {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
    
    @SuppressWarnings("rawtypes")
	@Transactional
    public List<Identifable> persistOrUpdateAll(Collection<Identifable> objects, Class clazz) {
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	Session session = (Session)entityManager.getDelegate();
    	List<Identifable> savedEntities = new ArrayList<>();

    	try {
    		FlushMode lastFlushMode = session.getFlushMode();
    		session.setFlushMode(FlushMode.MANUAL);
    		
    		for(Identifable obj: objects) {
    			if(obj.getId() == null || 
    					session.get(clazz, obj.getId()) == null){
    				session.save((Object)obj);
    				savedEntities.add(obj);
    			}
    		}
    		session.flush();
    		session.setFlushMode(lastFlushMode);
    		
    		session.close();
    	} catch (Throwable e) {
    		throw new RuntimeException(e.getMessage(),e);
    	}
    	
    	return savedEntities;
    }
    
    @SuppressWarnings("rawtypes")
	public Identifable persistSingle(Identifable identifable, Class clazz) {
    	Identifable savedEntity = null;
    	List<Identifable> objects = new ArrayList<>();
    	
    	objects.add(identifable);
    	objects = persistOrUpdateAll(objects, clazz);
    	
    	if(CollectionUtils.isNotEmpty(objects)) {
    		savedEntity = objects.get(0);
    	}
		
		return savedEntity;
    }
}
