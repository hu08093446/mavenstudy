package model;

public class ResultComplex {

    private String retCode;

    private String retDesc;

    private BaseResultInt result;

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

    public BaseResultInt getResult() {
        return result;
    }

    public void setResult(BaseResultInt result) {
        this.result = result;
    }
}
