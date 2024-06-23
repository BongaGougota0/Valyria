import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import model.Location;
import model.Person;

public class Main {

	public static void main(String[] args) throws Throwable {
		//
		Person misaria = new Person("Misaria White Wyrm");
		
		Lookup lookup = MethodHandles.lookup();
		Class<?> clss = lookup.findClass(Location.class.getName());
	
		MethodType mtype = MethodType.methodType(void.class);
		MethodHandle mh = lookup.findConstructor(clss, mtype);
		Location locationObj = (Location)mh.invoke();
		
		// Find private param and invoke set.
		Lookup privateLookup = MethodHandles.privateLookupIn(clss, lookup);
		MethodHandle m1 = privateLookup.findSetter(clss, "population", int.class);
		m1.invoke(locationObj, 12444);
		
		misaria.setLocation(locationObj);
		System.out.println("This persons location is "+misaria.getPersonLocation());
		
		
		locationObj.setLocationContinent("Lys");
		System.out.println("2nd try, This persons location is "+misaria.getPersonLocation());
		
		System.out.println("The population of "+misaria.getPersonLocation()+" is "+locationObj.getPopulation());
	}

}
