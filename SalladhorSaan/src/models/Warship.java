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
	
	private Warship() {}
	
	public String raisesBannerOf() {
		return this.ally;
	}
	
	public double suppliesCarried() {
		return this.supplies;
	}
	
	public int totalWarsFought() {
		return this.warExperience;
	}
}
