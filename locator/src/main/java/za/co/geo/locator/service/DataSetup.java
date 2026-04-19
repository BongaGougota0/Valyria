package za.co.geo.locator.service;

import org.redisson.api.RGeoReactive;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import za.co.geo.locator.config.RedissonConfig;
import za.co.geo.locator.model.GeoLocation;
import za.co.geo.locator.model.Restaurant;
import za.co.geo.locator.util.RestaurantUtil;

@Service
public class DataSetup implements CommandLineRunner {

    private RGeoReactive<Restaurant> geo;
    private RMapReactive<String, GeoLocation> map;
    private final RedissonConfig config = new RedissonConfig();

    private RedissonReactiveClient redissonReactiveClient;

    public DataSetup() {
        this.redissonReactiveClient = config.getReactiveClient();
    }

    @Override
    public void run(String... args) throws Exception {
        this.map = this.redissonReactiveClient.getMap("sa:restaurant", new TypedJsonJacksonCodec(String.class, GeoLocation.class));
        this.geo = this.redissonReactiveClient.getGeo("restaurants", new TypedJsonJacksonCodec(Restaurant.class));

        Flux.fromIterable(RestaurantUtil.getRestaurants())
                .flatMap(r -> this.geo.add(r.getLongitude(), r.getLatitude(), r).thenReturn(r))
                .flatMap(r -> this.map.fastPut(r.getZip(), GeoLocation.of(r.getLongitude(), r.getLatitude())))
                .subscribe();
    }
}
