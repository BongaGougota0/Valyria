import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import model.Drogon;
import model.Syrax;

public class Main {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException,
													IllegalArgumentException, IllegalAccessException,
													NoSuchMethodException, InvocationTargetException {
		Syrax daenys = new Syrax("Daenys", 13);
		
		Class<? extends Syrax> clss = daenys.getClass();
//		Field[] fields = clss.getFields();
		Field[] fields2 = clss.getDeclaredFields(); //Find private fields and change by reflection
//		Field ff = clss.getField("age"); // Doesn't work, age is a private field user getDeclaredField()...
		Field ff2 = clss.getDeclaredField("age");
		Field f2 = clss.getDeclaredField("mountedBy");
		f2.setAccessible(true);
		f2.set(daenys, "Daenys the Dreamer");
		ff2.setAccessible(true);
		ff2.set(daenys, 80);


		System.out.println("Dragons ages after edit field " + daenys.dragonAge());
		System.out.printf("This dragon is mounted by "+ daenys.riderMount());
		
		// More about class methods
		Method[] ms = clss.getDeclaredMethods();
		for(Method m : ms) {
			System.out.println("name of method is "+m.getName()+ " Modifier = "+m.getModifiers());
		
		}
		Method m = clss.getDeclaredMethod("dragonMatingRegion", null);
		m.setAccessible(true);
		System.out.println("Getting dragons mating location "+ m.invoke(daenys, null));
		
		//Invoking a private method
		Method m1 = clss.getDeclaredMethod("setDragonGender", String.class);
		m1.setAccessible(true);
		m1.invoke(daenys, "Male");
		
		Method dGender = clss.getDeclaredMethod("dragonGender", null);
		dGender.setAccessible(true);
		System.out.println("By default dragons are Hermaphrodites, this is a = "+ dGender.invoke(daenys, null));
		
	}
}
