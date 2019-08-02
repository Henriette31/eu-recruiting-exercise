package com.epiuse.recruiting;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.apache.olingo.odata2.core.rest.app.ODataApplication;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.mysql.cj.xdevapi.SessionFactory;


@Component
	@ApplicationPath("/odata")
	public class JerseyConfig extends ResourceConfig {
	
		public EntityManagerFactory entitymanagerfactory;
		
		
		public JerseyConfig(StudentODataJPAServiceFactory serviceFactory , EntityManagerFactory emf  ) {
			ODataApplication app = new ODataApplication();
			app.getClasses().forEach(c->{
				if(!ODataRootLocator.class.isAssignableFrom(c)) {
					register(c);
				}
			});
			register(new StudentRootLocator(serviceFactory));
			register(new EntityManagerFilter(emf));
			
		}
		
		@Provider
		public static class EntityManagerFilter implements ContainerRequestFilter , ContainerResponseFilter{
		
			public static final Logger log = LoggerFactory.getLogger(EntityManager.class);
			public static final String EM_REQUEST_ATTRIBUTE = EntityManagerFilter.class.getName() + "ENTITY_MANAGER";
			private final EntityManagerFactory emf;
		
		@Context
		private HttpServletRequest httpRequest;
		public EntityManagerFilter(EntityManagerFactory emf) {
			this.emf = emf;
		}
		
		@Override
		public void filter(ContainerRequestContext ctx) throws IOException{
			log.info("[I60] >>> filter");
			EntityManager em = this.emf.createEntityManager();
			httpRequest.setAttribute(EM_REQUEST_ATTRIBUTE, em);
			if(!"GET".equalsIgnoreCase(ctx.getMethod())) {
				em.getTransaction().begin();
			}
		}
			
			@Override
			public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException{
				log.info("[I60] >>> filter");
				EntityManager em=(EntityManager) httpRequest.getAttribute(EM_REQUEST_ATTRIBUTE);
			
			if(!"GET".equalsIgnoreCase(requestContext.getMethod())) {
				EntityTransaction t =em.getTransaction();
				if(t.isActive()) {
					if(!t.getRollbackOnly()) {
						t.commit();
					}
				}
			}
			em.close();
		}

	}
	@Path("/")
	public static class StudentRootLocator extends ODataRootLocator{
		private StudentODataJPAServiceFactory serviceFactory;
		public StudentRootLocator(StudentODataJPAServiceFactory serviceFactory) {
			this.serviceFactory = serviceFactory;
		}
		
		@Override
		public ODataServiceFactory getServiceFactory() {
			return this.serviceFactory;
		}
		
	}
	}


