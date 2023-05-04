package org.example.Commands;

import org.example.data.CollectionManager;

/**
 * Класс команды, сохраняющей коллекцию в файл
 */
public class SaveCommand implements Command {
    /**
     * Ссылка на объект класса CollectionManager
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     * @param collectionManager - менеджер коллекции с которым работает команда
     */
    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который исполняет команду
     */
    @Override
    public void execute() {
        collectionManager.saveCollection();
        System.out.println("Коллекция сохранена");
    }

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }
}