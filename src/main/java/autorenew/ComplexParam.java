package autorenew;

import java.util.*;

/**
 * 自动续订处理所需的参数，可以包罗万象
 */
public class ComplexParam {
    private List<String> strList = new ArrayList<>();

    private Set<String> strSet = new HashSet<>();

    private Map<String, Integer> strIntMap = new HashMap<>();

    public ComplexParam() {}

    public ComplexParam(List<String> strList, Set<String> strSet, Map<String, Integer> strIntMap) {
        this.strList = strList;
        this.strSet = strSet;
        this.strIntMap = strIntMap;
    }

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public Set<String> getStrSet() {
        return strSet;
    }

    public void setStrSet(Set<String> strSet) {
        this.strSet = strSet;
    }

    public Map<String, Integer> getStrIntMap() {
        return strIntMap;
    }

    public void setStrIntMap(Map<String, Integer> strIntMap) {
        this.strIntMap = strIntMap;
    }

    @Override
    public String toString() {
        return "ComplexParam{" +
                "strList=" + strList +
                ", strSet=" + strSet +
                ", strIntMap=" + strIntMap +
                '}';
    }
}
