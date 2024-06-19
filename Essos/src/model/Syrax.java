package model;

import modelInt.Dragon;
import modelInt.DragonInt;
import modelInt.DragonRegion;

public class Syrax extends DragonInt implements DragonRegion, Dragon{
	private String mountedBy;
	private int age;
	
	public Syrax(String dragonMounter, int age) {
		this.mountedBy = dragonMounter;
		this.age = age;
	}
	
	public Syrax(int age) {
		this.mountedBy = null;
		this.age = age;
	}

	@Override
	public String roar() {
		return "Soft roar. I'm a lady dragon!";
	}

	@Override
	public void attack() {
		System.out.print("Clench prey with my golden claws, Moderate bite impact.");
	}

	@Override
	public DragonRegion dragonBirthPlace() {
		return new DragonRegion() {

			@Override
			public String dragonBirthContinent() {
				return "Westeros";
			}
		};
	}

	@Override
	public String dragonBirthContinent() {
		return "Westeros";
	}

	@Override
	public String riderMount() {
		return (this.mountedBy.isEmpty() || this.mountedBy == null) ? "No Rider!" : this.mountedBy;
	}

	@Override
	public boolean wildDragon() {
		return (this.mountedBy.isEmpty() || this.mountedBy != null);
	}

	@Override
	public String birthContinent() {
		return dragonBirthContinent();
	}

	@Override
	public int dragonAge() {
		return this.age;
	}

	@Override
	public boolean hasRider() {
		return (this.mountedBy.isEmpty() && this.mountedBy != null);
	}

}
