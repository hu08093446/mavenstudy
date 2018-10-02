package learnspringaop;

public class MockTask implements ITask {
    @Override
    public void execute() {
        System.out.println("MockTask do something");
    }
}
