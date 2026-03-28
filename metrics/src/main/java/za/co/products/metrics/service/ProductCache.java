package za.co.products.metrics.service;

import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import za.co.products.metrics.model.Product;
import za.co.products.metrics.repo.ProductRepository;
import za.co.products.metrics.util.CacheTemplate;

@Service
public class ProductCache extends CacheTemplate<Integer, Product> {

    private final ProductRepository productRepository;
    private final RMapReactive<Integer, Product> map;

    public ProductCache(ProductRepository productRepository, RedissonReactiveClient client) {
        this.productRepository = productRepository;
        this.map = client.getMap("products", new TypedJsonJacksonCodec(Integer.class, Product.class));
    }

    @Override
    protected Mono<Product> getFromSource(Integer key) {
        return this.productRepository.findById(key);
    }

    @Override
    protected Mono<Product> getFromCache(Integer key) {
        return this.map.get(key);
    }

    @Override
    protected Mono<Product> updateSource(Integer key, Product entity) {
        return this.productRepository.findById(key)
                .doOnNext(p -> entity.setId(key))
                .flatMap(p -> this.productRepository.save(entity));
    }

    @Override
    protected Mono<Product> updateCache(Integer key, Product entity) {
        return this.map.fastPut(key, entity).thenReturn(entity);
    }

    @Override
    protected Mono<Void> deleteFromSource(Integer key) {
        return this.productRepository.deleteById(key);
    }

    @Override
    protected Mono<Void> deleteFromCache(Integer key) {
        return this.map.fastRemove(key).then();
    }
}