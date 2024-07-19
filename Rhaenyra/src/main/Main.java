package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import annotations.Location;
import bannermen.Velayrone;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Class<?> clss = Class.forName("bannermen.Velayrone");
		Constructor<?> c = clss.getConstructor(String.class, String.class);
		Velayrone v = (Velayrone) c.newInstance("Corlys","Vhagar");
		v.getFamilyDetails();
		
		Field[] fields = v.getClass().getDeclaredFields();
		
		for(Field f : fields) {
			if(f.isAnnotationPresent(Location.class)) {
				System.out.println("Field is annotated with "+ Location.class.getCanonicalName());
				System.out.println("Field name "+ f.getName());
				System.out.println("====================");
				Location l =  f.getAnnotation(Location.class);
				String setValue = l.value();
				System.out.println("Annotation default value is "+l.value());
				updateObject(v, setValue);
			}
		}

		v.getFamilyDetails();
	}
	
	public static <T> void updateObject(T t, String setValue) throws Exception {
		Field[] fs = t.getClass().getDeclaredFields();
		for(Field f : fs) {
			if(f.isAnnotationPresent(Location.class)) {
				f.setAccessible(true);
				f.set(t, setValue);
			}
		}
	}
}