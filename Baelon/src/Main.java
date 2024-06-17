import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import Model.Ship;

public class Main {
	//add Throws parent exception in the method signature, using try-catch will
	//result in a lot of exception clauses

	public static void main(String[] args) throws Exception {
		// Instantiate object Ship
//		Ship ship = new Ship(); #Doesn't work.
		
		Class<?> obj = Class.forName("Model.Ship");
		Constructor constructor = obj.getDeclaredConstructor();
		constructor.setAccessible(true);
		Ship ship = (Ship)constructor.newInstance();
		ship.getShip();
		
		Method[] methods = ship.getClass().getDeclaredMethods();
		//Find private method, make it accessible and call method.
		for(Method m : methods) {
			
			if(!m.canAccess(ship)) {
				m.setAccessible(true);
				m.invoke(ship);
			}
		}
	}
}
