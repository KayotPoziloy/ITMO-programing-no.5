package org.example.Commands;

import org.example.FileWork.HumanBeingReader;
import org.example.data.CollectionManager;
import org.example.data.HumanBeing;

/**
 * Класс команды, которая добавляет новые элементы в коллекцию
 */
public class AddCommand implements Command {
    /**
     * Менеджер коллекции
     */
    private final CollectionManager collectionManager;
    /**
     * Объект читающий информацию об элементе коллекции
     */
    private final HumanBeingReader humanBeingReader;

    /**
     * Конструктор класса
     * @param collectionManager - менеджер коллекции с которым работает команда
     * @param humanBeingReader  - менеджер коллекции с которым работает команда
     */
    public AddCommand(CollectionManager collectionManager, HumanBeingReader humanBeingReader) {
        this.collectionManager = collectionManager;
        this.humanBeingReader = humanBeingReader;
    }

    /**
     * Метод, исполняющий команду
     */
    @Override
    public void execute() {
        try {
            collectionManager.add(new HumanBeing(
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
            ));
            System.out.println("объект добавлен в коллекцию");
        } catch (Exception e) {
            System.err.println("ошибка в add" + e);
        }
    }

    /**
     * Метод, возвращающий описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "добавляет объект HumanBeing в коллекцию";
    }
}