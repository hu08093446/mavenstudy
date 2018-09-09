package learnaspect.mypac;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FuncPac {

    public Weather funcA(Date date, boolean flag) {
        System.out.println("this is funcA");
        Weather weather = new Weather();

        weather.setDate(date);
        weather.setSun(flag);

        return weather;
    }

    public Animal funcB(String name, boolean flag) {
        System.out.println("this is funcB");

        Animal animal = new Animal();
        animal.setName(name);
        animal.setIsroa(flag);

        return animal;
    }
}
