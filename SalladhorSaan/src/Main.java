import java.lang.reflect.Constructor;

import models.Warship;
import orm.Hibernate;

public class Main {

	public static void main(String[] args) throws Exception {
		Hibernate<Warship> hibernate = Hibernate.getConnection();
		Class<?> clss = Class.forName("models.Warship").getClass();
//		Constructor<? extends Object> cstr = clss.getConstructor(Warship.class);
		Constructor[] cs = clss.getConstructors();
		for(Constructor c : cs) {
			System.out.println("-"+c.getParameterTypes());
		}
//		cstr.setAccessible(true);
//		Warship warship = (Warship)cstr.newInstance();
//		
//		System.out.println(warship.toString());
		
	}
}
