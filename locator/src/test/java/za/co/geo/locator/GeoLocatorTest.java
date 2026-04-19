package za.co.geo.locator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.redisson.api.GeoUnit;
import org.redisson.api.RGeoReactive;
import org.redisson.api.RMapReactive;
import org.redisson.api.geo.GeoSearchArgs;
import org.redisson.api.geo.OptionalGeoSearch;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import za.co.geo.locator.config.BaseTest;
import za.co.geo.locator.dto.GeoLocation;
import za.co.geo.locator.dto.Restaurant;
import za.co.geo.locator.util.RestaurantUtilTest;
import java.util.function.Function;

public class GeoLocatorTest extends BaseTest {
    private RGeoReactive<Restaurant> restaurants;
    private RMapReactive<String, GeoLocation> map;

    @BeforeAll
    public void setGeoLocatorTest() {
        this.restaurants = this.reactiveClient.getGeo("restaurants", new TypedJsonJacksonCodec(Restaurant.class));
        this.map = this.reactiveClient.getMap("sa:restaurants", new TypedJsonJacksonCodec(String.class, GeoLocation.class));
    }

    @Test
    public void addRestaurants() {

        Mono<Void> mono = Flux.fromIterable(RestaurantUtilTest.getRestaurants())
                .flatMap(r -> this.restaurants.add(r.getLongitude(), r.getLatitude(), r).thenReturn(r))
                .flatMap(r -> this.map.fastPut(r.getZip(), GeoLocation.of(r.getLongitude(), r.getLatitude())))
//                .doOnNext(System.out::println)
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

    @Test
    public void searchByZipCode() {
        //
        Mono<Void> mono = this.map.get("4001")
                .map(gl -> GeoSearchArgs.from(gl.getLongitude(), gl.getLatitude()).radius(5, GeoUnit.KILOMETERS))
                .flatMap(r -> this.restaurants.search(r))
                .flatMapIterable(Function.identity())
                .doOnNext(System.out::println)
                .then();

        StepVerifier.create(mono).verifyComplete();
    }
}