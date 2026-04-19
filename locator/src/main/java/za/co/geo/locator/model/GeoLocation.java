package za.co.geo.locator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
//@AllArgsConstructor
@Getter @Setter
@AllArgsConstructor(staticName = "of")
public class GeoLocation {
    private double latitude;
    private double longitude;
}
