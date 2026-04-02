package za.co.transactions.AppTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.redisson.api.RedissonReactiveClient;
import za.co.transactions.AppTest.config.RedissonConfig;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    private final RedissonConfig redissonConfig = new RedissonConfig();
    protected RedissonReactiveClient reactiveClient;

    @BeforeAll
    public void setClient() {
        this.reactiveClient = this.redissonConfig.getReactiveClient();
    }

    @AfterAll
    public void shutDown() {
        this.reactiveClient.shutdown();
    }

}
