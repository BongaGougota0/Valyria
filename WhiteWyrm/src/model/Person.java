package model;

public class Person {
	private Location location = null;
	private String name;
	private int age;
	private String nativeLanguage;

	public Person(String personName) {
		this.name = personName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNativeLanguage() {
		return nativeLanguage;
	}

	public void setNativeLanguage(String nativeLanguage) {
		this.nativeLanguage = nativeLanguage;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getPersonLocation() {
		return this.location.getLocationContinent();
	}
	
}
