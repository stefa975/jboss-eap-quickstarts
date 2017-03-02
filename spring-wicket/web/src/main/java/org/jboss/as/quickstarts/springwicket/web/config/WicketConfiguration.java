package org.jboss.as.quickstarts.springwicket.web.config;

import org.jboss.as.quickstarts.springwicket.ejb.spring.PersonManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ejb.access.LocalStatelessSessionProxyFactoryBean;

import javax.naming.NamingException;

/**
 * Created by Stefan on 2017-03-02.
 */
@Configuration
public class WicketConfiguration {


    @Bean
    PersonManager personManager() throws NamingException {


        LocalStatelessSessionProxyFactoryBean fb = new LocalStatelessSessionProxyFactoryBean();
        // NOT OK
        //fb.setJndiName("java:module/PersonManager");
        // OK
        //fb.setJndiName("java:global/jboss-spring-wicket/jboss-spring-wicket-ejb/PersonManager");
        fb.setJndiName("java:app/jboss-spring-wicket-ejb/PersonManager");

        fb.setResourceRef(true);
        fb.setBusinessInterface(PersonManager.class);
        fb.setLookupHomeOnStartup(false);
//        fb.setJndiTemplate(jt);

        // Need lifecycle methods
        fb.afterPropertiesSet();
        return (PersonManager)fb.getObject();
    }

}
