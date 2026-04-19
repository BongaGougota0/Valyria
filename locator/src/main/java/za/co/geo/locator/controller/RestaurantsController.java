package za.co.geo.locator.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import za.co.geo.locator.model.Restaurant;
import za.co.geo.locator.service.RestaurantLocatorService;

import java.util.List;

@RestController
@RequestMapping("geo")
public class RestaurantsController {

    private final RestaurantLocatorService restaurantLocatorService;

    public RestaurantsController(RestaurantLocatorService restaurantLocatorService) {
        this.restaurantLocatorService = restaurantLocatorService;
    }

    @RequestMapping("/{zipCode}")
    public Flux<Restaurant> getRestaurants(@PathVariable String zipCode) {
        return this.restaurantLocatorService.getRestaurants(zipCode);
    }
}
