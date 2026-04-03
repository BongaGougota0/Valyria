package za.co.transactions.AppTest.PriorityQueuesUseCaseExercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.redisson.api.RScoredSortedSetReactive;
import org.redisson.codec.TypedJsonJacksonCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import za.co.transactions.AppTest.BaseTest;
import za.co.transactions.AppTest.config.PriorityQueues;
import java.time.Duration;
import static jodd.util.ThreadUtil.sleep;

public class PriorityQueuesUseCaseExample extends BaseTest {

    private PriorityQueues priorityQueue;

    @BeforeAll
    public void setUpQueue() {
        RScoredSortedSetReactive<UserOrder> queue = this.reactiveClient
                .getScoredSortedSet("queue:order", new TypedJsonJacksonCodec(UserOrder.class));
        priorityQueue = new PriorityQueues(queue);
    }

    @Test
    public void producer() {
        Flux.interval(Duration.ofSeconds(1))
                .map(i -> (i.intValue() *5))
                .doOnNext(i -> {
                    UserOrder order0 = new UserOrder(1,Order.GENERAL,"description "+ i,999.53);
                    UserOrder order1 = new UserOrder(2,Order.STANDARD,"description "+i,120.03);
                    UserOrder order2 = new UserOrder(3,Order.PREMIUM,"description "+i,122.53);
                    UserOrder order3 = new UserOrder(4,Order.STANDARD,"description "+i,12.39);
                    UserOrder order4 = new UserOrder(5,Order.PREMIUM,"description "+i,10.00);
                    UserOrder order5 = new UserOrder(6,Order.GENERAL,"description "+i,192.53);
                    Mono<Void> mono = Flux.just(order0, order1, order2, order3, order4, order5)
                            .flatMap(this.priorityQueue::addOrder)
                            .then();

                    StepVerifier.create(mono).verifyComplete();
                }).subscribe();
        sleep(20_000);
    }

    @Test
    public void consumer() {
        this.priorityQueue.takeItems()
                .delayElements(Duration.ofMillis(500))
                .doOnNext(System.out::println)
                .subscribe();
        sleep(300_000);
    }
}
