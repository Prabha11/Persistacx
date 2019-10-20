package persistacx;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Queue;

public class DaoService {
    public String getSetterNameFromGetterName(String getterName) {
        String nameWithoutGet = getterName.substring(3);
        return String.format("set%s", nameWithoutGet);
    }

    public Queue<Method> getMethodsByAnnotation(Class targetClass, Class annotationClass) {
        Queue<Method> methods = new LinkedList<>();

        Method[] allMethods = targetClass.getMethods();

        for (Method method : allMethods) {
            Annotation methodsAnnotation = method.getAnnotation(annotationClass);

            if (methodsAnnotation != null) {
                methods.add(method);
            }
        }

        return methods;
    }

    public Method getFirstMethodByAnnotation(Class targetClass, Class annotationClass) {

        Method[] allMethods = targetClass.getMethods();

        for (Method method : allMethods) {
            Annotation methodsAnnotation = method.getAnnotation(annotationClass);

            if (methodsAnnotation != null) {
                return method;
            }
        }

        return null;
    }

    public Queue<Method> getMethodsByAnnotationAddToQueue(Class targetClass, Class annotationClass, Queue<Method> methods) {
        Method[] allMethods = targetClass.getMethods();

        for (Method method : allMethods) {
            Annotation methodsAnnotation = method.getAnnotation(annotationClass);

            if (methodsAnnotation != null) {
                methods.add(method);
            }
        }

        return methods;
    }

    public String dataTypeMapJavaToSQL(Type javaType) {
        String sqlType = null;

        if (javaType == Integer.TYPE) {
            sqlType = "int";
        } else if (javaType == Float.TYPE) {
            sqlType = "float";
        } else {
            sqlType = "varchar(255)";
        }


        return sqlType;
    }
}
