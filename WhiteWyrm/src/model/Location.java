package model;

public class Location {
	private String locationName;
	private String locationContinent;
	private String language;
	private int population;

	public String getLocationName() {
		return locationName;
	}

	private void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationContinent() {
		return locationContinent;
	}

	public void setLocationContinent(String locationContinent) {
		this.locationContinent = locationContinent;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getPopulation() {
		return population;
	}

	private void setPopulation(int population) {
		this.population = population;
	}

	public Location() {}
	
}
