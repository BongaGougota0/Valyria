package za.co.geo.locator.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GeoLocation {
    private double latitude;
    private double longitude;
}
