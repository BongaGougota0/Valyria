package za.co.transactions.AppTest.PriorityQueuesUseCaseExercise;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserOrder {
//    @Id
    private int orderId;
    private Order order;
    private String orderDescription;
    private double orderValue;
}
