package org.example.Commands;

import org.example.FileWork.HumanBeingReader;
import org.example.User;
import org.example.data.CollectionManager;
import org.example.data.HumanBeing;

import java.util.InputMismatchException;

/**
 * Класс команды, которая добавляет новый объект HumanBeing, если его ImpactSpeed наименьший
 */
public class AddIfMinCommand implements Command {
    /**
     * Объект для ввода команд пользователем
     */
    User user;
    /**
     * Класс, считывающий параметры объекта HumanBeing
     */
    private HumanBeingReader humanBeingReader;
    /**
     * Менеджер коллекции
     */
    private CollectionManager collectionManager;

    /**
     * Конструктор класса
     * @param humanBeingReader  - менеджер коллекции с которым работает команда
     * @param collectionManager - менеджер коллекции с которым работает команда
     */
    public AddIfMinCommand(HumanBeingReader humanBeingReader, CollectionManager collectionManager) {
        this.humanBeingReader = humanBeingReader;
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, исполняющий команду
     */
    @Override
    public void execute() {
        try {
            HumanBeing newHuman = new HumanBeing(
                    humanBeingReader.setId(),
                    humanBeingReader.readName(),
                    humanBeingReader.readCoordinates(),
                    humanBeingReader.readCreationDate(),
                    humanBeingReader.readRealHero(),
                    humanBeingReader.readHasToothpick(),
                    humanBeingReader.readImpactSpeed(),
                    humanBeingReader.readSoundtrackName(),
                    humanBeingReader.readMinutesOfWaing(),
                    humanBeingReader.readWeaponType(),
                    humanBeingReader.readCar()
            );
            HumanBeing minHuman = collectionManager.getMinByImpactSpeed();
            if (minHuman == null || newHuman.getImpactSpeed() < minHuman.getImpactSpeed()) {
                collectionManager.add(newHuman);
            } else {
                user.printCommand("HumanBeing не был добавлен в коллекцию");
            }
        } catch (InputMismatchException e) {
            user.printError("неправильный формат ввода данных " + e);
        }
    }

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "создает новый объект HumanBeing, если его ImpactSpeed наименьший";
    }
}