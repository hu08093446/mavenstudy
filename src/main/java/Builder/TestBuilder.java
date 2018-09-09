package Builder;

public class TestBuilder {

    public static void main(String[] args) {
        NutritionFacts facts =
                new NutritionFacts.Builder(240, 8)
                        .calories(100)
                        .sodium(35)
                        .carbohydrate(222)
                        .fat(88)
                        .build();

        System.out.println(facts);
    }
}
