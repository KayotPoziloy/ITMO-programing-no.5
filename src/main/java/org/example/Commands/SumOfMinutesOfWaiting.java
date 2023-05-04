package org.example.Commands;

import org.example.data.CollectionManager;

/**
 * Команда, выводящая сумму минут ожидания всех объектов
 */
public class SumOfMinutesOfWaiting implements Command {
    /**
     * Ссылка на объект класса CollectionManager
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     * @param collectionManager - менеджер коллекции с которым работает команда
     */
    public SumOfMinutesOfWaiting(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, который исполняет команду
     */
    @Override
    public void execute() {
        try {
            double sum_of_minutes_of_waiting = collectionManager.getSumOfMinutesOfWaiting();
            System.out.println("сумма минут ожидания = " + sum_of_minutes_of_waiting);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "вывести сумму значений minutesOfWaiting каждого объекта";
    }
}