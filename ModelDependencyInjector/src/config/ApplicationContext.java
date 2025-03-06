package config;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import annotation.Autowired;
import annotation.Component;
import annotation.ComponentScan;
import annotation.Configuration;
import annotation.PostConstruct;

public class ApplicationContext {
    private static Map<Class<?>, Object> applicationObjects = new HashMap<>();

    public ApplicationContext(Class<ProjectConfig> clss) {
        Spring.initializer(clss);
    }

    public <T> T getBean(Class<T> clss) {
        T object = (T) applicationObjects.get(clss);
        if (object != null) {
            Field[] classFields = clss.getDeclaredFields();
            injectBean(object, classFields);
        }
        return object;
    }
    
    private <T> void init(T object) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(object);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public <T> void injectBean(T object, Field[] fields) {
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            Object objectsDependency = applicationObjects.get(type);
            if (field.isAnnotationPresent(Autowired.class)) {
                try {
                    field.set(object, objectsDependency);
                    Field[] declaredFields = type.getDeclaredFields();
                    injectBean(objectsDependency, declaredFields);
                    init(objectsDependency);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Spring {

        private static void initializer(Class<?> clss) {
            if (!clss.isAnnotationPresent(Configuration.class)) {
                throw new RuntimeException(String.format("Class %s does not contain configuration annotation.", clss.getName()));
            } else {
                ComponentScan annotation = clss.getAnnotation(ComponentScan.class);
                String value = annotation.value();
                String pkgStructure = String.format("bin/%s", value.replace(".", "/"));
                File[] files = findClasses(new File(pkgStructure));

                for (File file : files) {
                    String name = String.format("%s.%s", value, file.getName().replace(".class", ""));
                    try {
                        System.out.println("Loading class: " + name);
                        Class<?> loadedClass = Class.forName(name);
                        if (loadedClass.isAnnotationPresent(Component.class)) {
                            Constructor<?> constructor = loadedClass.getConstructor();
                            Object newInstance = constructor.newInstance();
                            applicationObjects.put(loadedClass, newInstance);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private static File[] findClasses(File file) {
            if (!file.exists()) {
                throw new RuntimeException("Package does not contain classes.");
            } else {
                File[] files = file.listFiles(f -> f.getName().endsWith(".class"));
                return files;
            }
        }
    }
}