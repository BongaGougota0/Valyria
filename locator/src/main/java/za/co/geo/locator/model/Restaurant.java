package za.co.geo.locator.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Restaurant {
    private String id;
    private String city;
    private double latitude;
    private double longitude;
    private String name;
    private String zip;
}