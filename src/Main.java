import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        Main programa = new Main();
        programa.inicio();
    }

    public void inicio() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Event> events = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\nBienvenido a Event Planner. Seleccione una opción:");
            System.out.println("[1] Añadir evento");
            System.out.println("[2] Borrar evento");
            System.out.println("[3] Listar eventos");
            System.out.println("[4] Marcar/desmarcar tarea de un evento como completada");
            System.out.println("[5] Salir");
            System.out.print("Elige una opción: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.print("Título del evento: ");
                    String title = scanner.nextLine();
                    System.out.print("Año: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Mes: ");
                    int month = Integer.parseInt(scanner.nextLine());
                    System.out.print("Día: ");
                    int day = Integer.parseInt(scanner.nextLine());
                    LocalDate date = LocalDate.of(year, month, day);
                    System.out.print("Prioridad (HIGH, MEDIUM, LOW): ");
                    Event.Priority priority = Event.Priority.valueOf(scanner.nextLine().toUpperCase());
                    Event event = new Event(title, date, priority);
                    System.out.print("¿Deseas añadir tareas? (s/n): ");
                    if (scanner.nextLine().equalsIgnoreCase("s")) {
                        while (true) {
                            System.out.print("Escribe una tarea (o deja vacío para terminar): ");
                            String taskText = scanner.nextLine();
                            if (taskText.isEmpty()) break;
                            event.addTask(taskText);
                        }
                    }
                    events.add(event);
                    System.out.println("Evento añadido con éxito.");
                }
                case "2" -> {
                    System.out.print("Título del evento a borrar: ");
                    String deleteTitle = scanner.nextLine();
                    events.removeIf(e -> e.toString().contains(deleteTitle));
                    System.out.println("Evento eliminado si existía.");
                }
                case "3" -> {
                    if (events.isEmpty()) {
                        System.out.println("No hay eventos registrados.");
                    } else {
                        events.forEach(System.out::println);
                    }
                }
                case "4" -> {
                    System.out.print("Título del evento: ");
                    String markTitle = scanner.nextLine();
                    Event targetEvent = events.stream()
                            .filter(e -> e.toString().contains(markTitle))
                            .findFirst().orElse(null);
                    if (targetEvent == null) {
                        System.out.println("Evento no encontrado.");
                        break;
                    }
                    System.out.println("Tareas del evento:");
                    System.out.println(targetEvent.listTasks());
                    System.out.print("Elige el número de la tarea a marcar/desmarcar: ");
                    int taskIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    targetEvent.getTask(taskIndex).toggleCompleted();
                    System.out.println("Tarea actualizada.");
                }
                case "5" -> {
                    running = false;
                    System.out.println("¡Gracias por usar Event Planner!");
                }
                default -> System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}

