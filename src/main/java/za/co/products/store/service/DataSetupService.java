package za.co.products.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.products.store.model.Product;
import za.co.products.store.repo.ProductRepository;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataSetupService implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;

    @Value("classpath:schema.sql")
    private Resource resource;

    @Override
    public void run(String... args) throws Exception {
        String query = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        System.clearProperty(query);

        Mono<Void> insert = Flux.range(1, 1000)
                .map(i -> new Product(null, "description of Product " + i,
                        ThreadLocalRandom.current().nextDouble(10.00, 1200.00)))
                .collectList()
                .flatMapMany(l -> this.productRepository.saveAll(l))
                .then();

        this.r2dbcEntityTemplate
                .getDatabaseClient()
                .sql(query)
                .then()
                .then(insert)
                .doFinally(s -> System.out.println("Data setup. " + s))
                .subscribe();
    }
}