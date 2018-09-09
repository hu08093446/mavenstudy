package basiclearn;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customerId;

    private List<OrderLine> orderLines = new ArrayList<>();

    public Order() {

    }

    public Order(String customerId, List<OrderLine> orderLines) {
        this.customerId = customerId;
        this.orderLines = orderLines;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerId='" + customerId + '\'' +
                ", orderLines=" + orderLines +
                '}';
    }
}
