package basiclearn.nopublicclass.inner;

import basiclearn.nopublicclass.PublicClass;

public class ClassB {
    public static void main(String[] args) {
        PublicClass sideClass = new PublicClass();
        // todo 下面这条语句居然可以，这难道没有访问person的tostring方法吗？
        // todo 可是Person的方法在包外应该无法访问的呀？？？
        System.out.println(sideClass.getPerson());
        // Person类（包括其所有属性和方法）只能在nopulicclass包的内部访问，
        // 任何其他的包（无论内外包）都访问不了
//        sideClass.getPerson().really();

    }
}
