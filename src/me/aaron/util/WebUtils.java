package me.aaron.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * 把requext对象中的请求参数封装到bean中
 */

public class WebUtils {

    public static <T> T request2Bean(HttpServletRequest request, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            Enumeration<String> nameList = request.getParameterNames();

            while (nameList.hasMoreElements()){
                String name = nameList.nextElement();
                String para = request.getParameter(name);
                BeanUtils.setProperty(bean,name,para);
            }

            return bean;
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static String makeId() {
        return UUID.randomUUID().toString();
    }

}
