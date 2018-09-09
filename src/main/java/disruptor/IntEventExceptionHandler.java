package disruptor;


import com.lmax.disruptor.ExceptionHandler;


public class IntEventExceptionHandler implements ExceptionHandler {

    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {
        System.out.println("handleEventException");
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        System.out.println("handleOnStartException");
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        System.out.println("handleOnShutdownException");
    }
}