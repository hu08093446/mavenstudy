package model;

public class BaseResult {

    private String retCode;

    private String retDesc;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetDesc() {
        return retDesc;
    }

    public void setRetDesc(String retDesc) {
        this.retDesc = retDesc;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "retCode='" + retCode + '\'' +
                ", retDesc='" + retDesc + '\'' +
                '}';
    }
}
