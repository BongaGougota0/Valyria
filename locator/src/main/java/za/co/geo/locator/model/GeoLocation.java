package za.co.geo.locator.model;

import lombok.*;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GeoLocation {
    private double latitude;
    private double longitude;
}
