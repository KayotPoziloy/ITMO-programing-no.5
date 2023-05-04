package org.example.Commands;

import org.example.data.CollectionManager;

/**
 * Класс команды, которая удаляет элемент HumanBeing с определенным id`
 */
public class RemoveByIDCommand implements CommandWithArguments {
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
    public RemoveByIDCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, приводящий команду в действие
     */
    @Override
    public void execute() {
        try {
            collectionManager.removeById(Integer.parseInt(commandArguments[0]));
            if (commandArguments[0].equals("")) throw new IndexOutOfBoundsException();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("не указан аргумент команды");
        } catch (NumberFormatException e) {
            System.err.println("формат аргумента должен быть целочисленным ");
        }
    }

    /**
     * Метод, получающий аргумент команды
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
        return "удаляет элемент коллекции с id равным заданному";
    }
}