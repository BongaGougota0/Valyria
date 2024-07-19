package bannermen;

import annotations.Location;

public class Velayrone {
	private String name;
	private String currentFamilyHead;
	private String largestDragon;
	
	@Location
	private String familyOrigin;
	
	@Location
	private String originContinent;
	
	public Velayrone(String familyHead, String largestDragon) {
		this.largestDragon = largestDragon;
		this.currentFamilyHead = familyHead;
	}

	public void getFamilyDetails() {
		System.out.println("class name "+this.getClass().getName() +"\n "+toString());
	}

	@Override
	public String toString() {
		return "Velayrone [name=" + name + ", currentFamilyHead=" + currentFamilyHead + ", largestDragon="
				+ largestDragon + ", familyOrigin=" + familyOrigin + ", originContinent=" + originContinent + "]";
	}

}
