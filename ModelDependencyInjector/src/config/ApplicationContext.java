package config;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import annotation.Autowired;
import annotation.Component;
import annotation.ComponentScan;
import annotation.Configuration;

public class ApplicationContext {
	private static Map<Class<?>, Object> applicationObjects = new HashMap<>();
	
	public ApplicationContext(Class<ProjectConfig> clss) {
		Spring.initializer(clss);
	}
	
	public <T> T getBean(Class<T> clss) {
		T object = (T)applicationObjects.get(clss);
		Field[] classFields = clss.getDeclaredFields();
		injectBean(object, classFields);
		return object;
	}
	
	public <T> void injectBean(T object, Field[] fields) {
		for(Field field : fields) {
			field.setAccessible(true);
			Class<?> type = field.getType();
			Object objectsDependency = applicationObjects.get(type);
			if(field.isAnnotationPresent(Autowired.class)) {
				try {
					field.set(type, objectsDependency);
					Field[] declaredFields = type.getDeclaredFields();
					injectBean(objectsDependency, declaredFields);
					
				}catch(IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class Spring{
		
		private static void initializer(Class<?> clss) {
			if(!clss.isAnnotationPresent(Configuration.class)) {
				throw new RuntimeException(String.format("Class %s doesnot contain configuration anotation.", clss.getName()));
			}else {
				ComponentScan annotation = clss.getAnnotation(ComponentScan.class);
				String value = annotation.value();
				String pkgStructure = String.format("bin/", value.replace(".", "/"));
				File[] files = findClasses(new File(pkgStructure));
				
				for(File file : files) {
					String name = String.format("%s.%s", value, file.getName().replace(".class", ""));
					try {
						Class<?> loadedClass = Class.forName(name);
						if(loadedClass.isAnnotationPresent(Component.class)) {
							Constructor<?> constructor = loadedClass.getConstructor();
							Object newInstance = constructor.newInstance();
							applicationObjects.put(loadedClass, newInstance);
						}else {
							
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
		private static File[] findClasses(File file) {
			if(!file.exists()) {
				throw new RuntimeException("Package");
			}else {
				File[] files = file.listFiles(f -> f.getName().endsWith(".class"));
				return files;
			}
		}
		
	}
}
