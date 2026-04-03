package za.co.transactions.AppTest.config;

import org.redisson.api.RScoredSortedSetReactive;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.transactions.AppTest.BaseTest;
import za.co.transactions.AppTest.PriorityQueuesUseCaseExercise.Order;
import za.co.transactions.AppTest.PriorityQueuesUseCaseExercise.UserOrder;

public class PriorityQueues extends BaseTest {
    private RScoredSortedSetReactive<UserOrder> userOrders;

    public PriorityQueues(RScoredSortedSetReactive<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    public Mono<Void> addOrder(UserOrder order) {
        return this.userOrders.add(
                createScore(order.getOrder()),
                order).then();
    }

    public Flux<UserOrder> takeItems() {
        return this.userOrders.takeFirstElements().limitRate(1);
    }

    private double createScore(Order order) {
        return order.ordinal() + Double.parseDouble("0"+System.nanoTime());
    }
}
