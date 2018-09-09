package learngeneric;

public class Single<T> {
    T data;

    public Single() {
        this.data = null;
    }

    public Single(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Single{" +
                "data=" + data +
                '}';
    }
}
