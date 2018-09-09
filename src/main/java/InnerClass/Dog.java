package InnerClass;

public class Dog {

    public interface Pet {
        public void beFriend();
        public void play();
    }

    public static void main(String[] args) {
        Pet pet = new Pet() {
            public void beFriend() {
                System.out.println("噌噌。");
            }

            public void play() {
                System.out.println("玩一玩。");
            }
        };

        pet.beFriend();
        pet.play();
    }
}
