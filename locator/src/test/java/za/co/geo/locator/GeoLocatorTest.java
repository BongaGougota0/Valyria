package za.co.geo.locator;

import org.junit.jupiter.api.Test;
import org.redisson.api.RGeoReactive;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import za.co.geo.locator.config.BaseTest;
import za.co.geo.locator.model.Restaurant;
import za.co.geo.locator.model.util.RestaurantUtil;

public class GeoLocatorTest extends BaseTest {

    @Test
    public void addRestaurants() {
        RGeoReactive<Restaurant> restaurants = this.reactiveClient.getGeo("restaurants", new TypedJsonJacksonCodec(Restaurant.class));
        Mono<Void> mono = Flux.fromIterable(RestaurantUtil.getRestaurants())
                .flatMap(r -> restaurants.add(r.getLongitude(), r.getLatitude(), r))
                .then();

        StepVerifier.create(mono).verifyComplete();
    }

    @Test
    public void searchForRestaurant() {

    }
}