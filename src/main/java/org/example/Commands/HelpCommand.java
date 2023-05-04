package org.example.Commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс команды help
 * выводит список доступных команд
 */
public class HelpCommand implements Command{
    /**
     * Коллекция, содержащая объекты команд без аргументов
     */
    private HashMap<String, Command> commandWithoutArguments;
    /**
     * Коллекция, содержащая объекты команд с аргументами
     */
    private HashMap<String, CommandWithArguments> commandWithArguments;

    /**
     * Конструктор класса
     * @param commandHashMap              Коллекция, содержщая все доступные команды без аргументов
     * @param commandWithArgumentsHashMap Коллекция, содержащая все доступные команды с аргументами
     */
    public HelpCommand(HashMap<String, Command> commandHashMap,
                       HashMap<String, CommandWithArguments> commandWithArgumentsHashMap) {
        this.commandWithoutArguments = commandHashMap;
        this.commandWithArguments = commandWithArgumentsHashMap;
    }

    /**
     * Метод, который исполняет команду
     */
    @Override
    public void execute() {
        for (Map.Entry<String, Command> entry : commandWithoutArguments.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());
        }
        for (Map.Entry<String, CommandWithArguments> entry : commandWithArguments.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());
        }
    }

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "вывести справку по всем доступным командам";
    }
}