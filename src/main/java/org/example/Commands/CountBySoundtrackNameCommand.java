package org.example.Commands;

import org.example.data.CollectionManager;

/**
 * Класс команды, которая считает количество саундтреков с заданным именем
 */
public class CountBySoundtrackNameCommand implements CommandWithArguments {
    /**
     * Ссылка на объект класса CollectionManager
     */
    private CollectionManager collectionManager;
    /**
     * Поле хранит массив аргументов команды
     */
    private String[] commandArguments;

    /**
     * Конструктор класса
     * @param collectionManager - менеджер коллекции с которым работает команда
     */
    public CountBySoundtrackNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, приводящий команду в действие
     */
    @Override
    public void execute() {
        try {
            int count = collectionManager.countBySoundtrackName(String.join(" ", commandArguments));
            if (commandArguments[0].equals("")) throw new IndexOutOfBoundsException();
            System.out.println("количество саундтреков с названием " + String.join(" ", commandArguments) + " - " + count);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("не указан аргумент команды");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Метод, получающий аргумент команды
     * @param commandArguments Аргументы команды.
     */
    @Override
    public void getCommandArguments(String[] commandArguments) {
        this.commandArguments = commandArguments;
        String commandArgument = String.join(" ", commandArguments);
    }

    /**
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return CommandWithArguments.super.getDescription();
    }
}