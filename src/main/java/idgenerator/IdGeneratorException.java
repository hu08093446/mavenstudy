package idgenerator;

public class IdGeneratorException extends RuntimeException {

    private String entityType;

    public IdGeneratorException() {
        super();
    }

    public IdGeneratorException(String entityType) {
        super();
        this.entityType = entityType;
    }

    public IdGeneratorException(String entityType, String message) {
        super(message);
        this.entityType = entityType;
    }

    public IdGeneratorException(Throwable throwable) {
        super(throwable);
    }

    public IdGeneratorException(String entityType, String message, Throwable throwable) {
        super(message, throwable);
        this.entityType = entityType;
    }
}
