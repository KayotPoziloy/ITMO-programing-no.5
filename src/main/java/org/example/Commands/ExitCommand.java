package org.example.Commands;

/**
 * Класс команды, которая закрывает приложение
 */
public class ExitCommand implements Command {
    /**
     * Метод, который исполняет команду
     */
    @Override
    public void execute() {
        System.out.println("Выход из программы (без сохранения)");
        System.exit(0);
    }

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "завершает работу программы без сохранения в файл";
    }
}