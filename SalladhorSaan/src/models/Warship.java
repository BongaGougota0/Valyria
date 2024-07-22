package models;

import annotations.Column;
import annotations.SerialNumber;

public class Warship extends Ship{
	
	@SerialNumber
	private long id;
	
	@Column
	private String ally;
	private double supplies;
	@Column
	private int warExperience;
	@Column
	public String shipName;
	
	private Warship() {}
	
	public String raisesBannerOf() {
		return this.ally;
	}
	
	public String getAlly() {
		return ally;
	}

	public void setAlly(String ally) {
		this.ally = ally;
	}

	public double getSupplies() {
		return supplies;
	}

	public void setSupplies(double supplies) {
		this.supplies = supplies;
	}

	public int getWarExperience() {
		return warExperience;
	}

	public void setWarExperience(int warExperience) {
		this.warExperience = warExperience;
	}

	public double suppliesCarried() {
		return this.supplies;
	}
	
	public int totalWarsFought() {
		return this.warExperience;
	}

	@Override
	public String toString() {
		return "Warship [id=" + id + ", ally=" + ally + ", supplies=" + supplies + "\n, warExperience=" + warExperience
				+ ", shipName=" + shipName + ", depatureLocation=" + depatureLocation + ", destination=" + destination
				+ ", pirateShip=" + pirateShip + ", warShip=" + warShip + "\n, captain=" + captain
				+ ", totalCountOfShipPassengers=" + totalCountOfShipPassengers + "]";
	}
	
}
