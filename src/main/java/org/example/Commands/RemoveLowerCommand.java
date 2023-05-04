package org.example.Commands;

import org.example.data.CollectionManager;

public class RemoveLowerCommand implements CommandWithArguments {
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
    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который исполняет команду
     */
    @Override
    public void execute() {
        try {
            collectionManager.removeLower(Integer.parseInt(commandArguments[0]));
            if (commandArguments[0].equals("")) throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("не указан аргумент команды");
        } catch (NumberFormatException e) {
            System.err.println("формат аргумента должен быть целочисленным " + e);
        }
    }

    /**
     * Метод, который получает аргумент для команды
     * @param commandArguments Аргументы команды.
     */
    @Override
    public void getCommandArguments(String[] commandArguments) {
        this.commandArguments = commandArguments;
    }

    /**
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "удаляет все элементы коллекции, значение id которых меньше указанного в качестве атрибута команды";
    }
}