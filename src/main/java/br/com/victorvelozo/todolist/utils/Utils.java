package br.com.victorvelozo.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertieNames(source));
    }

    private static String[] getNullPropertieNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] properties = src.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<String>();

        for (PropertyDescriptor property : properties) {
            Object propetyValue = src.getPropertyValue(property.getName());
            if (propetyValue == null) {
                nullProperties.add(property.getName());
            }
        }

        String[] result = new String[nullProperties.size()];
        return nullProperties.toArray(result);
    }
}
