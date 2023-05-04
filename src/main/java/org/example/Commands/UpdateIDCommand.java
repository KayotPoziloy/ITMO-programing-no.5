package org.example.Commands;

import org.example.FileWork.HumanBeingReader;
import org.example.User;
import org.example.data.*;

import java.util.NoSuchElementException;

/**
 * Класс команды, которая обновляет объект по id
 */
public class UpdateIDCommand implements CommandWithArguments {
    /**
     * Объект класса HumanBeingReader
     */
    private HumanBeingReader humanBeingReader;
    /**
     * Поле, хранящее ссылку на объект CollectionManager
     */
    private CollectionManager collectionManager;
    /**
     * Поле, хранящее ссылку на объект User
     */
    private User user;
    /**
     * Массив аргументов команды
     */
    private String[] commandArguments;

    /**
     * Конструктор класса
     * @param humanBeingReader  - класс, который считывает объект HumanBeing
     * @param collectionManager - менеджер коллекции с которым работает команда
     */
    public UpdateIDCommand(HumanBeingReader humanBeingReader, CollectionManager collectionManager) {
        this.humanBeingReader = humanBeingReader;
        this.collectionManager = collectionManager;
    }

    /**
     * Метод исполняющий команду
     */
    @Override
    public void execute() {
        try {
            Integer id = Integer.parseInt(commandArguments[0]);

            HumanBeing humanBeing = collectionManager.getByID(id);
            if (humanBeing == null) throw new NoSuchElementException();
            collectionManager.removeById(id);

            collectionManager.add(new HumanBeing(
                    id,
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
            ));
            user.printCommand("персонаж изменен");
        } catch (NoSuchElementException e) {
            user.printError("персонажа с таким id нет");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * @param commandArguments Аргументы команды.
     */
    @Override
    public void getCommandArguments(String[] commandArguments) {
        this.commandArguments = commandArguments;
    }

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "изменяет объект коллекции с заданным id";
    }
}