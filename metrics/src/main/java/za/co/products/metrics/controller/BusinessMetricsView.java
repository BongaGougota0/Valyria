package za.co.products.metrics.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import za.co.products.metrics.service.BusinessMetrics;
import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("business")
public class BusinessMetricsView {
    private final BusinessMetrics service;

    public BusinessMetricsView(BusinessMetrics service) {
        this.service = service;
    }

    @GetMapping(value = "/metrics", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<Integer, Double>> getTop5Products() {
        return this.service.top5Products()
                .repeatWhen(l -> Flux.interval(Duration.ofSeconds(3)));
    }
}
