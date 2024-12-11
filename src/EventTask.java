public class EventTask {
    private String text;
    private boolean isCompleted;

    public EventTask(String text) {
        this.text = text;
        this.isCompleted = false;
    }
    public void toggleCompleted() {
        isCompleted = !isCompleted;
    }

}
