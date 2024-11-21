package Lab2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;

public class DescribeCall {
    public void repeatAnn(Object object) throws InvocationTargetException, IllegalAccessException {
        Class<?> newClass = object.getClass();
        Method[] items = newClass.getDeclaredMethods();
        for (Method item : items) {
            if (item.isAnnotationPresent(SubAnnotation.class)) {
                if (Modifier.isPrivate(item.getModifiers()) | Modifier.isProtected(item.getModifiers())) {
                    item.setAccessible(true);
                    int counter = item.getAnnotation(SubAnnotation.class).value();
                    for (int i = 0; i < counter; i++) {
                        Object[] parameters = getParameters(item);
                        item.invoke(object, parameters);
                    }
                        item.setAccessible(false);
                    }
                }
            }
        }

    private Object[] getParameters(Method item) {
        Object[] parameters = new Object[item.getParameterCount()];
        Random random = new Random();
        for (int i = 0; i < parameters.length; ++i) {
            if (item.getParameters()[i].getType().equals(String.class)) {
                parameters[i] = "CO2";
            } else if (item.getParameters()[i].getType().equals(int.class)) {
                parameters[i] = random.nextInt(100, 500);
            } else if (item.getParameters()[i].getType().equals(double.class)) {
                parameters[i] = random.nextDouble(1, 500);
            } else {
                parameters[i] = " ";
            }
        }
        return parameters;
    }
}
