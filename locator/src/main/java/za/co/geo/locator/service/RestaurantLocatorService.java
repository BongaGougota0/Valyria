package za.co.geo.locator.service;

import org.redisson.api.GeoUnit;
import org.redisson.api.RGeoReactive;
import org.redisson.api.RMapReactive;
import org.redisson.api.geo.GeoSearchArgs;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import za.co.geo.locator.config.RedissonConfig;
import za.co.geo.locator.model.GeoLocation;
import za.co.geo.locator.model.Restaurant;
import java.util.function.Function;

@Service
public class RestaurantLocatorService {

    private RGeoReactive<Restaurant> geoRestaurant;
    private RMapReactive<String, GeoLocation> mapRestaurant;
    private final RedissonConfig config = new RedissonConfig();

    public RestaurantLocatorService() {
        this.geoRestaurant = config.getReactiveClient().getGeo("restaurants", new TypedJsonJacksonCodec(Restaurant.class));
        this.mapRestaurant = config.getReactiveClient().getMap("sa:restaurant", new TypedJsonJacksonCodec(String.class, GeoLocation.class));
    }

    public Flux<Restaurant> getRestaurants(String zipCode) {
        return this.mapRestaurant.get(zipCode)
                .map(gl -> GeoSearchArgs
                        .from(gl.getLongitude(), gl.getLatitude()).radius(5, GeoUnit.KILOMETERS))
                .flatMap(gs -> this.geoRestaurant.search(gs))
                .flatMapIterable(Function.identity());
    }
}
