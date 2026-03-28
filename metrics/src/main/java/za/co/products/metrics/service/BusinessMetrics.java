package za.co.products.metrics.service;

import org.redisson.RedissonReactive;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RScoredSortedSetReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BusinessMetrics {
    private final RedissonReactiveClient client;

    public BusinessMetrics(RedissonReactiveClient client) {
        this.client = client;
    }

    public Mono<Map<Integer, Double>> top5Products(){
        String format = DateTimeFormatter.ofPattern("YYYYMMdd").format(LocalDate.now());
        RScoredSortedSetReactive<Integer> set = this.client.getScoredSortedSet("products"+ format, IntegerCodec.INSTANCE);
        return  set.entryRangeReversed(0,4)
                .map(listSe -> listSe.stream()
                        .collect(Collectors.toMap(
                                ScoredEntry::getValue,
                                ScoredEntry::getScore,
                                (a, b) -> a,
                                LinkedHashMap::new
                        )));
    }
}
