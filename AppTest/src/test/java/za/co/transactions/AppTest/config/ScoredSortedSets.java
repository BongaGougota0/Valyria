package za.co.transactions.AppTest.config;

import org.junit.jupiter.api.Test;
import org.redisson.api.RScoredSortedSetReactive;
import org.redisson.client.codec.StringCodec;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import za.co.transactions.AppTest.BaseTest;
import java.util.function.Function;

public class ScoredSortedSets extends BaseTest {

    private RScoredSortedSetReactive<String> scoredSortedSet;

    @Test
    public void sortedSet() {
         this.scoredSortedSet = this.reactiveClient.getScoredSortedSet("student:avg", StringCodec.INSTANCE);
        Mono<Void> mono = scoredSortedSet.addScore("Thandi", 90)
                .then(scoredSortedSet.add(67, "Amanda"))
                .then(scoredSortedSet.addScore("Bongani", 78.0))
                .then(scoredSortedSet.addScore("Thembi", 76))
                .then(scoredSortedSet.add(79.7, "Oratile"))
                .then();

        StepVerifier.create(mono).verifyComplete();
    }

    @Test
    public void getByOrder() {
        this.scoredSortedSet = this.reactiveClient.getScoredSortedSet("student:avg", StringCodec.INSTANCE);
//        Mono<Void> mono = this.scoredSortedSet.entryRange(0, -1) // get in asc order. Line 34: Get in descending order
        Mono<Void> mono = this.scoredSortedSet.entryRangeReversed(0, -1)
                .flatMapIterable(Function.identity())
                .map(se -> se.getScore() + ":" + se.getValue())
                .doOnNext(System.out::println)
                .then();

        StepVerifier.create(mono).verifyComplete();
    }

}
