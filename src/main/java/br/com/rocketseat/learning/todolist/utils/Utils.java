package br.com.rocketseat.learning.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    public static void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source){
        
        // Permite mapear meta informacoes de um objeto
        final BeanWrapper src= new  BeanWrapperImpl(source);

        // Obtendo a lista de nome das propriedades do objeto recebido como recurso/source/origem.
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // Tipo de lista
        Set<String> emptyNames = new HashSet<>();

        // similar ao conhecido foreach [ usado para percorrer colecoes ou listas ]
        for(PropertyDescriptor pd: pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                emptyNames.add(pd.getName());
            }
        }
        
        String[] result = new String[emptyNames.size()];

        return emptyNames.toArray(result);
    }
}
