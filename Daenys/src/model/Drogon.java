package model;

import modelInt.DragonInt;
import modelInt.DragonRegion;

public class Drogon extends DragonInt{
	
	private String aboutDrogon() {
		return "Modern big black dragon, likely descendent from Seasmoke.";
	}

	@Override
	public String roar() {
		return "Deep thundering roar like Khal Drogo";
	}

	@Override
	public void attack() {
		System.out.print("Spit Fire! Launch with my giant claws.");
	}

	// For some reason, Lambda didn't work.
	@Override
	public DragonRegion dragonBirthPlace() {
		DragonRegion region = new DragonRegion() {
		public String dragonBirthContinent() {
				return "Pentos";
			}
		};
		return region;
	}


}
