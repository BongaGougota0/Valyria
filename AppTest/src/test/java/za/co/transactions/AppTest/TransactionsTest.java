package za.co.transactions.AppTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucketReactive;
import org.redisson.api.RTransactionReactive;
import org.redisson.api.TransactionOptions;
import org.redisson.client.codec.LongCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class TransactionsTest extends BaseTest {

    private RBucketReactive<Long> user1Balance;
    private RBucketReactive<Long> user2Balance;

    @BeforeAll
    public void setBalances() {
        this.user1Balance = this.reactiveClient.getBucket("user:1:balance", LongCodec.INSTANCE);
        this.user2Balance = this.reactiveClient.getBucket("user:2:balance", LongCodec.INSTANCE);
        Mono<Void> mono= this.user2Balance.set(2300L)
                .then(this.user1Balance.set(300L))
                .doOnNext(System.out::println)
                .then();

        StepVerifier.create(mono).verifyComplete();
    }

    @Test
    public void transactionTest() {
         RTransactionReactive transaction = this.reactiveClient.createTransaction(TransactionOptions.defaults());
         Mono<Void> mono = this.doMoneyTransfer(this.user1Balance, this.user2Balance, 45)
//                .thenReturn(0) // Simulate an error
                .doOnError(System.out::println)
                 .then(transaction.commit())
                 .doOnError(ex -> transaction.rollback())
                 .then();

         StepVerifier.create(mono).verifyComplete();
    }

    private Mono<Void> doMoneyTransfer(RBucketReactive<Long> user1, RBucketReactive<Long> user2, int amount) {
        return Flux.zip(user1.get(), user2.get())
                .filter(t -> t.getT1() >= amount)
                .flatMap(t -> user1.set(t.getT1() - amount).thenReturn(t))
                .flatMap(t -> user2.set(t.getT2()+amount))
                .then();
    }

    @AfterAll
    public void displayAccountBalances(){
        RBucketReactive<Long> user2Balance = this.reactiveClient.getBucket("user:1:balance", LongCodec.INSTANCE);
        RBucketReactive<Long> user1Balance = this.reactiveClient.getBucket("user:2:balance", LongCodec.INSTANCE);
        Mono<Void> mono = Flux.zip(user1Balance.get(), user2Balance.get())
                .doOnNext(System.out::println)
                .then();

        StepVerifier.create(mono).verifyComplete();
    }
}
