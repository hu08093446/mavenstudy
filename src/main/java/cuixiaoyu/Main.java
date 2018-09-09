package cuixiaoyu;

public class Main {

    boolean isPrime(int i) {
        if (i == 1 || i == 2) {
            return true;
        }
        for (int j = 2; j < Math.floor(Math.sqrt(i)); j++) {
            if (i % j == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.isPrime(18));
    }
}
