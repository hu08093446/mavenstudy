package basiclearn;

public class OrderLine {
    private Double price;

    private Integer num;

    private String lineId;

    public OrderLine() {

    }

    public OrderLine(String lineId, Integer num) {
        this.lineId = lineId;
        this.num = num;
    }

    public OrderLine(Double price, Integer num, String lineId) {
        this.price = price;
        this.num = num;
        this.lineId = lineId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "price=" + price +
                ", num=" + num +
                ", lineId='" + lineId + '\'' +
                '}';
    }
}
