package org.example;

import org.example.Commands.CommandManager;
import org.example.FileWork.FileManager;
import org.example.FileWork.HumanBeingReader;
import org.example.data.CollectionManager;
import org.example.data.HumanBeing;

import java.util.NoSuchElementException;

/**
 * Класс, через который запускается приложение
 */
public class Application {
    /**
     * Менеджер коллекции
     */
    CollectionManager collectionManager;
    /**
     * Менеджер файлов
     */
    FileManager fileManager;
    /**
     * Объект производящий чтение и вывод команд
     */
    User user;
    /**
     * Менеджер команд
     */
    CommandManager commandManager;
    /**
     * Объект производящий чтение объекта HumanBeing
     */
    HumanBeingReader humanBeingReader;

    /**
     * Метод, который выполняет запуск программы
     * Через этот метод проходит вся работа программы
     * @param inputFile имя файла, из которого читаются объекты коллекции, представленные в формате json
     */
    public void start(String inputFile) {
        collectionManager = new CollectionManager();
        fileManager = new FileManager();
        user = new User();

        humanBeingReader = new HumanBeingReader(user);

        this.commandManager = new CommandManager(collectionManager, user, inputFile, humanBeingReader);

        try {
            HumanBeing[] humanBeings = fileManager.parseToCollection(inputFile);
            for (HumanBeing humanBeing : humanBeings) {
                collectionManager.add(humanBeing);
            }
            user.printCommand("\nЭлементы коллекции из файла загружены\n");

        } catch (NumberFormatException e) {
            System.err.println("Ошибка со считыванием данных " + e);
            System.exit(-1);
        } catch (Exception e) {
            System.err.println("Непредвиденная ошибка " + e);
            System.exit(-1);
        }
        try {
            cycle();
        } catch (NoSuchElementException e) {
            System.err.println("ошибка");
        }
    }

    /**
     * Метод, который циклично читает команды из консоли
     */
    public void cycle() {
        user.printCommand("Программа запущена");
        while (true) {
            user.printCommand("\nВведите команду:");
            user.preamble();
            String line = user.readLine();
            commandManager.execute(line);
        }
    }
}