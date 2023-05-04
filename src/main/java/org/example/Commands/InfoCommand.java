package org.example.Commands;

import org.example.data.CollectionManager;

/**
 * Команда для вывода информации коллекции
 */

public class InfoCommand implements Command {
    /**
     * Ссылка на объект класса CollectionManager
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     * @param collectionManager - хранит ссылку на объект CollectionManager
     */
    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который исполняет команду
     */
    @Override
    public void execute() {
        collectionManager.info();
    }

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "информация о коллекции";
    }
}