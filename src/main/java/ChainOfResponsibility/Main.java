package ChainOfResponsibility;

import ChainOfResponsibility.PeopleProcess.AbstractProcess;
import ChainOfResponsibility.PeopleProcess.FemaleProcess;
import ChainOfResponsibility.PeopleProcess.MaleProcess;
import ChainOfResponsibility.model.OriginParam;
import ChainOfResponsibility.model.ParamObject;

public class Main {
    public static void main(String[] args) {
        processMale();
        processFemale();
    }

    private static void processMale() {
        AbstractProcess process = new MaleProcess();
        ParamObject destObject = new ParamObject();
        process.process(generateMaleParam(), destObject);
        System.out.println(destObject);
    }

    private static void processFemale() {
        AbstractProcess process = new FemaleProcess();
        ParamObject destObject = new ParamObject();
        process.process(generateFemaleParam(), destObject);
        System.out.println(destObject);
    }

    private static OriginParam generateMaleParam() {
        OriginParam maleParam = new OriginParam();
        maleParam.setAge(30);
        maleParam.setGender("male");
        maleParam.setName("Huke");

        return maleParam;
    }

    private static OriginParam generateFemaleParam() {
        OriginParam femaleParam = new OriginParam();
        femaleParam.setAge(36);
        femaleParam.setGender("female");
        femaleParam.setName("Lishuang");

        return femaleParam;
    }
}
