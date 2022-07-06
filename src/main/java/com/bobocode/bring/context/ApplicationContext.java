package com.bobocode.bring.context;

import java.util.List;

public interface ApplicationContext {

    <T> T getBean(Class<T> beanType);

    <T> T getBean(String beanName, Class<T> beanType);

    Object getBean(String beanName);

    <T> List<T> getAllBeans(Class<T> beanType);
}
