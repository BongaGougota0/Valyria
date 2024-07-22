package models;

import annotations.Column;

public abstract class Ship {
	

	public String depatureLocation;
	public String destination;
	public boolean pirateShip;
	public boolean warShip;
	@Column
	public String captain;
	private double distanceSailed = 0.0;
	@Column
	public int totalCountOfShipPassengers = 0;
	
	public double distanceSailed() {
		return this.distanceSailed;
	}
	
	public int totalCountOfShipPassengers() {
		return this.totalCountOfShipPassengers;
	}
}
