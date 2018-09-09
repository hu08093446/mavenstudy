package basiclearn;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class FloatDoubleTest {

    @Test
    public void floatDoubleCompute() {
        double funds = 1.00;
        int itemsBought = 0;
        for(double price = 0.1; funds >= price; price += 0.1) {
            itemsBought++;
            funds = funds - price;
        }
        System.out.println(itemsBought + " items bought");
        System.out.println("Change: " + funds);
    }

    @Test
    // 由于精度限制，本来不想等的数确相等了
    public void floatDoubleEqual() {
        float a = (float) 0.111111111112;
        float b = (float) 0.111111111119;

        double c = 0.11111111111111111111111119;
        double d = 0.11111111111111111111111118;

        Assert.assertTrue(a == b);
        Assert.assertTrue(c == d);
        // 比较float和double是否相等不要用==，而要判断两者的误差是否在可接受范围内
        Assert.assertTrue(Math.abs(c - d) < 0.000000000000001);
    }

    @Test
    public void bigDecimalCompute() {
        double a = 0.1;
        // 此处直接使用double类型，那么最终的结果是不对的，因为double本身就是不精确的，必须使用String类型
        final BigDecimal TEN_CENTS = new BigDecimal(String.valueOf(a));

        int itemBoughts = 0;
        BigDecimal funds = new BigDecimal("1.0");
        for(BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
            itemBoughts++;
            funds = funds.subtract(price);
        }

        System.out.println("itemBought: " + itemBoughts);
        System.out.println("money remains: " + funds);
    }
}
