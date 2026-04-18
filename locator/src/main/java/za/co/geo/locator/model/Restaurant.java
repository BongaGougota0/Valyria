package za.co.geo.locator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Restaurant {
    private String id;
    private String city;
    private double latitude;
    private double longitude;
    private String name;
    private String zip;
}