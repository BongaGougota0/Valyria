package models;

import java.util.ArrayList;

public class House {

	private String houseName;
	private String kingdom;
	private String lord;
	public ArrayList<Warship> armada = new ArrayList<>();
	
	
	public House(String houseName, String lord) {
		this.houseName = houseName;
		this.lord = lord;
	}

	

	public String getKingdom() {
		return kingdom;
	}


	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}


	public ArrayList<Warship> getArmada() {
		return armada;
	}



	public void setArmada(ArrayList<Warship> armada) {
		this.armada = armada;
	}


}
