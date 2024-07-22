import java.lang.reflect.Constructor;

import models.Warship;
import orm.Hibernate;

public class Main {

	public static void main(String[] args) throws Exception {
		Hibernate<Warship> hibernate = Hibernate.getConnection();
		Class<?> clss = Class.forName("models.Warship");
		Constructor<?> cstr = clss.getDeclaredConstructor();
		cstr.setAccessible(true);
		Warship warship = (Warship)cstr.newInstance();
		warship.setAlly("Stark");
		warship.setSupplies(2444.44);
		warship.setWarExperience(120);
		
		// Check before writing.
		System.out.println(warship.toString());
		// Write to db.
		hibernate.objectRepository(warship);
		
	}
}
