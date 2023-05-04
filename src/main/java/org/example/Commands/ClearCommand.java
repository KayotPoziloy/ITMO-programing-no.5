package org.example.Commands;

import org.example.data.CollectionManager;

/**
 * Класс команды, которая очищает коллекцию объектов
 */
public class ClearCommand implements Command {
    /**
     * Ссылка на объект класса CollectionManager
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     * @param collectionManager - менеджер коллекции с которым работает команда
     */
    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который исполняет команду
     */
    @Override
    public void execute() {
        collectionManager.clear();
    }

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }
}