package basiclearn;

import java.util.Objects;

// 具体的枚举对象其实也是静态成员，由于它们只能放在最前面，所以在类加载时率先被初始化
// 而且初始化工作是调用对应的构造函数进行的
public enum GenericEnum {
    ONE(1), TWO(2), THREE(3);

    private Integer value;

    private GenericEnum() {
    }

    private GenericEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static boolean isContain(Integer value) {
        boolean result = false;

        for(GenericEnum gEnum : GenericEnum.values()) {
            if(Objects.equals(value, gEnum.getValue())) {
                result = true;
                break;
            }
        }

        return result;
    }

}
