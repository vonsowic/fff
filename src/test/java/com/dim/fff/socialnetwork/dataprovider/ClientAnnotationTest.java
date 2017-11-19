package com.dim.fff.socialnetwork.dataprovider;

import org.junit.Test;
import org.reflections.Reflections;

import java.util.Set;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 19.11.17
 */
public class ClientAnnotationTest {

    @Test
    public void test(){
        Reflections reflections = new Reflections();
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Client.class);
        annotated.forEach(System.out::println);
    }
}
