package org.jboss.as.quickstarts.springwicket.ejb.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 * Created by Stefan on 2017-03-02.
 */
public class ExtendedSpringBeanAutowiringInterceptor extends SpringBeanAutowiringInterceptor {
    @Override
    protected BeanFactory getBeanFactory(Object target) {
        return new AnnotationConfigApplicationContext(EjbConfig.class);
    }

}
