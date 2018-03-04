package org.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;
import org.test.domain.DocumentWrapper;

@Component
public class MongoConfigPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof RepositoryRestConfiguration){
            ((RepositoryRestConfiguration) bean).exposeIdsFor(DocumentWrapper.class);
        }
        return bean;
    }

}
