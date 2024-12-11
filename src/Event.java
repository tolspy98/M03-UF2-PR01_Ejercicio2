import java.time.LocalDate;
import java.util.ArrayList;
public class Event {
    private String title;
    private LocalDate date;
    private Priority priority;
    private ArrayList<EventTask> tasks;

    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    public Event(String title, LocalDate date, Priority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.tasks = new ArrayList<>();
    }

    public void addTask(String taskText) {
        tasks.add(new EventTask(taskText));
    }

    public EventTask getTask(int index) {
        return tasks.get(index);
    }

    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public String toString() {
        long completedTasks = tasks.stream().filter(task -> task.toString().contains("Completada")).count();
        return "Evento: " + title + " | Fecha: " + date + " | Prioridad: " + priority +
                " | Tareas: " + completedTasks + "/" + tasks.size();
    }

}
