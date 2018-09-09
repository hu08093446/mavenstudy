package regex;

import com.alibaba.fastjson.JSON;
import model.BaseResult;
import model.BaseResultInt;
import model.ResultComplex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

    public static String getMatcher(String regex, String source) {
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result = matcher.group(0);
        }
        return result;
    }

    public static void main(String[] args) {
//        String url = "http://172.12.1.123/test.txt";
//        String regex = "((\\d{1,3})\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
//        System.out.println(getMatcher(regex,url));

        String str1 = "retCode";
        String str2 = "200";
        String regexA = "(\\\"" + str1 + "\\\"" + ":.{0,1}" + str2 + ")";
//        System.out.println(regexA);
//        String regex = "(\"retCode\":.{0,1}200)";

        BaseResult result = new BaseResult();
        result.setRetCode("20");
        result.setRetDesc("yyf fail.");
        String regexResult = JSON.toJSONString(result);
        System.out.println(regexResult);
        System.out.println(getMatcher(regexA, regexResult));

        BaseResultInt resultInt = new BaseResultInt();
        resultInt.setRetCode(200);
        resultInt.setRetDesc("yyf success.");
        String regexResultInt = JSON.toJSONString(resultInt);
        System.out.println(regexResultInt);
        System.out.println(getMatcher(regexA, regexResultInt));

        ResultComplex resultComplex = new ResultComplex();
        resultComplex.setResult(resultInt);
        resultComplex.setRetCode("200");
        resultComplex.setRetDesc("complex result");
        String complexResult = JSON.toJSONString(resultComplex);
        System.out.println(complexResult);
        System.out.println(getMatcher(regexA, complexResult));

    }
}
