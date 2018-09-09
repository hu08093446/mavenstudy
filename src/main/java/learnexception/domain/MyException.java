package learnexception.domain;

public class MyException extends RuntimeException {

    private String errorCode;

    private boolean isPropertiesKey = true;

    public MyException(){

    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(String errorCode, String message) {

        this(errorCode, message, true);
    }

    public MyException(String errorCode, String message, Throwable cause) {
        this(errorCode, message, cause, true);
    }

    public MyException(String errorCode, String message, Throwable cause, boolean isPropertiesKey) {
        super(message, cause);
        // 构造函数内，不要使用可能会被重载的方法
        this.errorCode = errorCode;
        this.isPropertiesKey = isPropertiesKey;
    }

    public MyException(String errorCode, String message, boolean isPropertiesKey) {
        // attention 构造器中第一行必须是super()或者this()，所以两者不能共存
        // 如果两者都没写，那就是调用了父类默认的构造函数，也就是默认的super()
        super(message);
        this.errorCode = errorCode;
        this.isPropertiesKey = isPropertiesKey;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPropertiesKey() {
        return isPropertiesKey;
    }

    public void setPropertiesKey(boolean propertiesKey) {
        isPropertiesKey = propertiesKey;
    }
}
