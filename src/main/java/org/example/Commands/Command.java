package org.example.Commands;

/**
 * Интерфейс исполнения команды
 */
public interface Command {
    /**
     * Метод, который исполняет команду
     */
    public void execute();

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    default String getDescription() {
        return "Описание этой команды еще не готово";
    }
}