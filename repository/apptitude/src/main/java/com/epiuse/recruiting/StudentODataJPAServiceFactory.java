package com.epiuse.recruiting;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.SynchronizationType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.servlet.http.HttpServletRequest;

import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import com.epiuse.recruiting.JerseyConfig.EntityManagerFilter;

@Component
public class StudentODataJPAServiceFactory extends ODataJPAServiceFactory{
private static final Logger log = LoggerFactory.getLogger(StudentODataJPAServiceFactory.class);

public  StudentODataJPAServiceFactory() {
	// TODO Auto-generated constructor stub
	setDetailErrors(true);
}

	@Override
	public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException{
		log.info("[I32] >>> initializeODataJPAContext()");
		ODataJPAContext ct = getODataJPAContext();
		ODataContext oct = ct.getODataContext();
		HttpServletRequest request = (HttpServletRequest) oct.getParameter(ODataContext.HTTP_SERVLET_REQUEST_OBJECT);
		EntityManager em = (EntityManager) request.getAttribute(EntityManagerFilter.EM_REQUEST_ATTRIBUTE);
		
		ct.setEntityManager(em);
		ct.setPersistenceUnitName("default");
		ct.setContainerManaged(true);
		return ct;
		
	}
	
	static  class EntityManagerWrapper implements EntityManager{
		private EntityManager delegate;
		
		public void persist(Object entity) {
			log.info("[I68] merge: enity.class=)"+ entity.getClass().getSimpleName());
			delegate.persist(entity);
			//FLushes the output stream and forces an buffered output bytes ti be written ou
			//delegate.flush();
		}
		
		public <T> T merge(T entity) {
		log.info("[I74] merge: entity.c);ass="+entity.getClass().getSimpleName());	
		delegate.remove(entity);
		return delegate.merge(entity);
		}
		public void remove(Object entity) {
            log.info("[I78] remove: entity.class=" + entity.getClass()
                .getSimpleName());
            delegate.remove(entity);
        }

        public <T> T find(Class<T> entityClass, Object primaryKey) {
            return delegate.find(entityClass, primaryKey);
        }
        //
        
        public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
            return delegate.find(entityClass, primaryKey, properties);
        }

        public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
            return delegate.find(entityClass, primaryKey, lockMode);
        }

        public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
            return delegate.find(entityClass, primaryKey, lockMode, properties);
        }

        public <T> T getReference(Class<T> entityClass, Object primaryKey) {
            return delegate.getReference(entityClass, primaryKey);
        }

        public void flush() {
            delegate.flush();
        }

        public void setFlushMode(FlushModeType flushMode) {
            delegate.setFlushMode(flushMode);
        }

        public FlushModeType getFlushMode() {
            return delegate.getFlushMode();
        }

        public void lock(Object entity, LockModeType lockMode) {
            delegate.lock(entity, lockMode);
        }

        public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
            delegate.lock(entity, lockMode, properties);
        }

        public void refresh(Object entity) {
            delegate.refresh(entity);
        }

        public void refresh(Object entity, Map<String, Object> properties) {
            delegate.refresh(entity, properties);
        }

        public void refresh(Object entity, LockModeType lockMode) {
            delegate.refresh(entity, lockMode);
        }

        public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
            delegate.refresh(entity, lockMode, properties);
        }

        public void clear() {
            delegate.clear();
        }

        public void detach(Object entity) {
            delegate.detach(entity);
        }

        public boolean contains(Object entity) {
            return delegate.contains(entity);
        }

        public LockModeType getLockMode(Object entity) {
            return delegate.getLockMode(entity);
        }

        public void setProperty(String propertyName, Object value) {
            delegate.setProperty(propertyName, value);
        }

        public Map<String, Object> getProperties() {
            return delegate.getProperties();
        }

        public Query createQuery(String qlString) {
            return delegate.createQuery(qlString);
        }

        public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
            return delegate.createQuery(criteriaQuery);
        }

        public Query createQuery(CriteriaUpdate updateQuery) {
            return delegate.createQuery(updateQuery);
        }

        public Query createQuery(CriteriaDelete deleteQuery) {
            return delegate.createQuery(deleteQuery);
        }

        public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
            return delegate.createQuery(qlString, resultClass);
        }

        public Query createNamedQuery(String name) {
            return delegate.createNamedQuery(name);
        }

        public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
            return delegate.createNamedQuery(name, resultClass);
        }

        public Query createNativeQuery(String sqlString) {
            return delegate.createNativeQuery(sqlString);
        }

        public Query createNativeQuery(String sqlString, Class resultClass) {
            return delegate.createNativeQuery(sqlString, resultClass);
        }

        public Query createNativeQuery(String sqlString, String resultSetMapping) {
            return delegate.createNativeQuery(sqlString, resultSetMapping);
        }

        public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
            return ((EntityManagerWrapper) delegate).createNamedStoredProcedureQuery(name);
        }

        public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
            return ((EntityManagerWrapper) delegate).createStoredProcedureQuery(procedureName);
        }

        public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
            return ((EntityManagerWrapper) delegate).createStoredProcedureQuery(procedureName, resultClasses);
        }

        public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
            return ((EntityManagerWrapper) delegate).createStoredProcedureQuery(procedureName, resultSetMappings);
        }

        public void joinTransaction() {
            delegate.joinTransaction();
        }

        public boolean isJoinedToTransaction() {
            return ((EntityManagerWrapper) delegate).isJoinedToTransaction();
        }

        public <T> T unwrap(Class<T> cls) {
            return delegate.unwrap(cls);
        }

        public Object getDelegate() {
            return delegate.getDelegate();
        }

        public void close() {
            log.info("[I229] close");
            delegate.close();
        }

        public boolean isOpen() {
            boolean isOpen = delegate.isOpen();
            log.info("[I236] isOpen: " + isOpen);
            return isOpen;
        }

        public EntityTransaction getTransaction() {
            log.info("[I240] getTransaction()");
            return delegate.getTransaction();
        }

        public EntityManagerFactory getEntityManagerFactory() {
            return delegate.getEntityManagerFactory();
        }

        public CriteriaBuilder getCriteriaBuilder() {
            return delegate.getCriteriaBuilder();
        }

        public Metamodel getMetamodel() {
            return delegate.getMetamodel();
        }

        public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
            return ((EntityManagerWrapper) delegate).createEntityGraph(rootType);
        }

        public EntityGraph<?> createEntityGraph(String graphName) {
            return ((EntityManagerWrapper) delegate).createEntityGraph(graphName);
        }

        public EntityGraph<?> getEntityGraph(String graphName) {
            return ((EntityManagerWrapper) delegate).getEntityGraph(graphName);
        }

        public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
            return ((EntityManagerWrapper) delegate).getEntityGraphs(entityClass);
        }

        public EntityManagerWrapper(EntityManager delegate) {
            this.delegate = delegate;
        }
	}
}
 


