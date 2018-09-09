package staticlearn;


class Father {
    public static void funcA() {
        System.out.println("this is funcA for father");
    }

    public static void funcB() {
        System.out.println("this is funcB for father");
    }
}

class Children extends Father {
    public static void funcA() {
        System.out.println("this is funcA for children");
    }

    public static void funcC() {
        System.out.println("this is funcC for children");
    }
}

public class LearnStatic {
    public static void main(String[] args) {
        Children.funcA();
        Children.funcB();
        Father.funcA();
        Children.funcC();
    }
}
