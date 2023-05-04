package org.example.Commands;

import org.example.data.CollectionManager;

/**
 * Команда, которая выводит уникальные значения параметра MinutesOfWaiting
 */
public class UniqueMinutesOfWaitingCommand implements Command {
    /**
     * Ссылка на объект класса CollectionManager
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     * @param collectionManager - хранит ссылку на объект CollectionManager
     */
    public UniqueMinutesOfWaitingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который исполняет команду
     */
    @Override
    public void execute() {
        collectionManager.printUniqueMinutesOfWaiting();
    }

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "печатает уникальные значения параметра MinutesOfWaiting";
    }
}