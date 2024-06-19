import java.lang.reflect.Method;

import model.Drogon;

public class Main {

	public static void main(String[] args) {
		Drogon daenerysMount = new Drogon();
		Class<? extends Drogon> daenerysDragon = daenerysMount.getClass();
		Method[] objUndeclaredMethod = daenerysDragon.getMethods();
		
		System.out.println("---------------------- Getting object methods by reflection.");
		for(Method m : objUndeclaredMethod) {
			System.out.println(" "+m);
		}
		
		
		Method[] objMethods = daenerysDragon.getDeclaredMethods();
		System.out.println("---------------------- Getting declared methods.");
		for(Method m : objMethods) {
			System.out.println(" "+m);
		}
		
		daenerysMount.attack();
		
	}
}
