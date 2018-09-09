package basiclearn;

public class Car {
    private String brand;

    private String corp;

    private double prive;

    public Car() {}

    // 通过这种构造函数注入的方式，可以在spring容器初始化的同时去做一些事情
    // 譬如可以在init方法中启动一些线程去做某些工作
    public Car(String brand, String corp, double prive) {
        this.brand = brand;
        this.corp = corp;
        this.prive = prive;

        init();
    }

    private void init() {
        System.out.println("-----------------constructor inject");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public double getPrive() {
        return prive;
    }

    public void setPrive(double prive) {
        this.prive = prive;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", corp='" + corp + '\'' +
                ", prive=" + prive +
                '}';
    }
}
