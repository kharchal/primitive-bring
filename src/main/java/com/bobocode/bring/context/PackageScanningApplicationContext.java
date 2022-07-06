package com.bobocode.bring.context;

import com.bobocode.bring.annotation.Bean;
import com.bobocode.bring.annotation.Inject;
import com.bobocode.bring.exception.NoSuchBeanException;
import com.bobocode.bring.exception.NoUniqueBeanException;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PackageScanningApplicationContext implements ApplicationContext {

    private Map<String, Object> context = new HashMap<>();

    @SneakyThrows
    public PackageScanningApplicationContext(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Bean.class);
        for (Class<?> type : classes) {
            Constructor<?> constructor = type.getConstructor();
            Object newBean = constructor.newInstance();
            String value = type.getAnnotation(Bean.class).value();
            String name = value.isBlank() ? type.getSimpleName() : value;
            context.put(name, newBean);
        }
        for (Object o : context.values()) {
            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    String name = field.getAnnotation(Inject.class).value();
                    Class<?> fieldType = field.getType();
                    Object value = name.isBlank() ? getBean(fieldType) : getBean(name);
                    if (value == null) {
                        throw new NoSuchBeanException();
                    }
                    field.setAccessible(true);
                    field.set(o, value);
                }
            }
        }
    }

    @Override
    public <T> T getBean(Class<T> beanType) {
        List<Object> list = context.values()
                .stream()
                .filter(b -> b.getClass().isAssignableFrom(beanType))
                .collect(Collectors.toList());

        if (list.size() > 1) {
            throw new NoUniqueBeanException();
        }
        Object o = list.get(0);
        if (o == null) {
            throw new NoSuchBeanException();
        }

        return beanType.cast(o);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanType) {
        return beanType.cast(getBean(beanName));
    }

    @Override
    public Object getBean(String beanName) {
        return context.get(beanName);
    }

    @Override
    public <T> List<T> getAllBeans(Class<T> beanType) {
        return context.values().stream()
                .filter( b -> b.getClass().isAssignableFrom(beanType))
                .map(t -> beanType.cast(t))
                .collect(Collectors.toList());
    }
}
