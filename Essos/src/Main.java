import java.lang.reflect.Method;

import model.Drogon;
import model.Syrax;

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
		
		System.out.println();
		Syrax syrax = new Syrax("Rhaenyra", 20);
		Syrax sheepStealer = new Syrax(12);
		System.out.println("Is this a wild dragon? "+syrax.wildDragon());
		System.out.println("Mounter of the dragon ? "+ syrax.riderMount());
		
		System.out.println();
		System.out.println("Is this a wild dragon? "+sheepStealer.wildDragon());
		System.out.println("Mounter of the dragon ? "+ sheepStealer.riderMount());
		
	}
}
