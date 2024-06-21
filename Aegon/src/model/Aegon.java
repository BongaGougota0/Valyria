package model;

public class Aegon {
	private String name;
	private String surname;
	private String continentOfBirth;
	private static String swordName = "Blackfyre";
	private int age;
	
	public Aegon(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	public void setContinentOfBirth(String continentOfBirth) {
		this.continentOfBirth = continentOfBirth;
	}
	
	public String birthContinent() {
		return this.continentOfBirth;
	}
	
	public String getFullName() {
		return this.name +" "+this.surname;
	}
	
	public String swordName() {
		return swordName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
