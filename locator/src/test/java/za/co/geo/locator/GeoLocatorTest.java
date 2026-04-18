package za.co.geo.locator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.redisson.api.GeoUnit;
import org.redisson.api.RGeoReactive;
import org.redisson.api.geo.GeoSearchArgs;
import org.redisson.api.geo.OptionalGeoSearch;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import za.co.geo.locator.config.BaseTest;
import za.co.geo.locator.model.Restaurant;
import za.co.geo.locator.model.util.RestaurantUtil;
import java.util.function.Function;

public class GeoLocatorTest extends BaseTest {
    private RGeoReactive<Restaurant> restaurants;

    @BeforeAll
    public void SetGeoLocatorTest() {
        this.restaurants = this.reactiveClient.getGeo("restaurants", new TypedJsonJacksonCodec(Restaurant.class));;
    }

    @Test
    public void addRestaurants() {

        Mono<Void> mono = Flux.fromIterable(RestaurantUtil.getRestaurants())
                .flatMap(r -> restaurants.add(r.getLongitude(), r.getLatitude(), r))
                .then();

        StepVerifier.create(mono).verifyComplete();
    }

    @Test
    public void searchForRestaurant() {
        // demo coord for searching nearby restaurants.
        // lat : -33.9127
        // lon : 19.1235
        OptionalGeoSearch radius = GeoSearchArgs.from(19.1235, -33.9127).radius(3, GeoUnit.KILOMETERS);
        restaurants.search(radius)
                .flatMapIterable(Function.identity())
                .doOnNext(restaurant -> {
                    System.out.println(restaurant.getName());
                })
                .subscribe();
    }
}