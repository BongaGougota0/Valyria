import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import model.Aegon;

public class Main {

	public static void main(String[] args) throws Throwable {
		Aegon egg = new Aegon("Aegon","Targaryen");
		egg.setAge(24);
		
		Lookup lookup = MethodHandles.lookup();
		Class<?> clss = lookup.findClass(Aegon.class.getName());
		
		MethodType mt = MethodType.methodType(int.class);
		MethodHandle mh = lookup.findVirtual(clss, "getAge", mt);
		
		MethodType mtFname = MethodType.methodType(String.class);
		MethodHandle mhFname = lookup.findVirtual(clss, "getFullName", mtFname);
		
		MethodType mtb = MethodType.methodType(String.class);
//		MethodHandle mb = lookup.findVirtual(clss, "getBirthContinent", mtb);
		MethodHandle mb = lookup.findVirtual(clss, "birthContinent", mtb);
		
		System.out.println(mhFname.invoke(egg)+"'s age is "+mh.invoke(egg)+" years old.\n born in "+mb.invoke(egg));
		System.out.println("--------- invoking set method -----------");
		
		MethodType setB = MethodType.methodType(void.class, String.class);
		MethodHandle mhSet = lookup.findVirtual(clss, "setContinentOfBirth", setB);
		mhSet.invoke(egg,"Old Valyria");
		
		System.out.println(mhFname.invoke(egg)+"'s age is "+mh.invoke(egg)+" years old.\n born in "+mb.invoke(egg));
	}
}
