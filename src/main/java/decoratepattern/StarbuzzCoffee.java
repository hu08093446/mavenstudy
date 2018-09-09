package decoratepattern;

import java.util.Random;

public class StarbuzzCoffee {

    public static void main(String[] args) {
        final Integer Size = 20;

        final Integer max = 4;
        final Integer min = 1;
        Random random = new Random();

        Integer result = 0;
        Beverage beverage = null;
        for(int i = 0; i < Size; i++) {

//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            result = random.nextInt(max - min + 1) + min;
            switch (result) {
                case 1:
                    beverage = new Espresso();
                    break;
                case 2:
                    beverage = new DarkRoast();
                    beverage = new Mocha(beverage);
                    beverage = new Mocha(beverage);
                    beverage = new Whip(beverage);
                    break;
                case 3:
                    beverage = new HouseBlend();
                    beverage = new Soy(beverage);
                    beverage = new Mocha(beverage);
                    beverage = new Whip(beverage);
                    break;
                default:
                    beverage = new Decaf();
                    beverage.setSize(CUPSIZE.TAIL);
                    beverage = new Soy(beverage);
                    beverage = new Mocha(beverage);
            }

            if(null != beverage) {
                System.out.println(beverage.getDescription() + " $"
                        + beverage.cost());
            }
        }

//        Beverage beverage1 = new Espresso();
//        System.out.println(beverage1.getDescription() + " $"
//                + beverage1.cost());
//
//        Beverage beverage2 = new DarkRoast();
//        beverage2 = new Mocha(beverage2);
//        beverage2 = new Mocha(beverage2);
//        beverage2 = new Whip(beverage2);
//        System.out.println(beverage2.getDescription() + " $"
//                + beverage2.cost());
//
//        Beverage beverage3 = new HouseBlend();
//        beverage3 = new Soy(beverage3);
//        beverage3 = new Mocha(beverage3);
//        beverage3 = new Whip(beverage3);
//        System.out.println(beverage3.getDescription() + " $"
//                + beverage3.cost());

    }
}
