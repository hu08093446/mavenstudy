package basiclearn.polymorphism;

public class ActorMain {
    public static void main(String[] args) {
        // act()方法YongHappyActor类中有重写，所以直接调用
        // sing()方法没有重写，则向父类中找，父类重写，调用父类的
        // old()方法yong和happy都没有重写，则一直向上找，直到找到顶层基类
        Actor actor = new YongHappyActor();
        actor.act();
        actor.sing();
        actor.old();
    }
}
