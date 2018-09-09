package disruptor;

public class TradeTransaction {

    private String name;

    public TradeTransaction() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TradeTransaction{" +
                "name='" + name + '\'' +
                '}';
    }
}
