package org.example;

/**
 * Класс запуска программы
 */
public class Main {
    /**
     * Метод запускающий приложение
     */
    public static void main(String[] args) {
        String envVariable = System.getenv("Lab5");
        if (args.length > 0) {
            if (!args[0].equals("")) {
                Application application = new Application();
                application.start(args[0]);
            }
        } else {
            Application application = new Application();
            application.start(envVariable);
        }
    }
}