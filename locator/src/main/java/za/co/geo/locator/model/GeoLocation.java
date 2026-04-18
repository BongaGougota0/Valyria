package za.co.geo.locator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class GeoLocation {
    private double latitude;
    private double longitude;
}
