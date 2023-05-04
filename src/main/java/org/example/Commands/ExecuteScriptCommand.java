package org.example.Commands;

import org.example.FileWork.HumanBeingReader;
import org.example.User;
import org.example.data.CollectionManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс команды, которая запускает скрипт
 */
public class ExecuteScriptCommand implements CommandWithArguments {
    /**
     * Массив, хранящий аргументы команды
     */
    private String[] commandArguments;
    /**
     * Объект класса CollectionManager
     */
    private CollectionManager collectionManager;
    /**
     * Объект класса User
     */
    private User user;
    /**
     * Объект, производящий чтение полей
     */
    private HumanBeingReader humanBeingReader;
    /**
     * Адрес файла, из которого исполняется скрипт
     */
    private String scriptPath;
    /**
     * Поле, хранящее объект класса ExecuteScript.Script
     */
    private Script script;

    /**
     * Конструктор класса
     * @param collectionManager - объект класса менеджера коллекции
     * @param humanBeingReader  - объект класса, читающего параметры объекта
     * @param script            - объект класса ExecuteScript.Script, из которого получаем список скриптов
     */
    public ExecuteScriptCommand(CollectionManager collectionManager, HumanBeingReader humanBeingReader, Script script) {
        this.collectionManager = collectionManager;
        this.humanBeingReader = humanBeingReader;
        this.script = script;
    }

    /**
     * Метод, который исполняет команду
     */
    @Override
    public void execute() {
        try {
            if (commandArguments.length == 1) {
                scriptPath = commandArguments[0];
                if (!script.scriptPaths.contains(scriptPath)) {
                    script.putScript(scriptPath);
                }
            } else {
                throw new IllegalArgumentException();
            }

            File ioFile = new File(scriptPath);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();

            FileInputStream fileInputStream = new FileInputStream(scriptPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            Scanner scanner = new Scanner(inputStreamReader);
            user = new User(scanner);
            CommandManager commandManager = new CommandManager(collectionManager, user, humanBeingReader, script);

            while (scanner.hasNext()) {
                commandManager.execute(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл скрипта не найден " + e);
        } catch (NullPointerException e) {
            System.err.println("Не выбран файл, из которого читать скрипт");
        } catch (IOException e) {
            System.err.println("Доступ к файлу невозможен " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("скрипт не передан в качестве аргумента команды, либо кол-во агрументов больше 1");
        }
        script.removeScript(scriptPath);
    }

    /**
     * Метод, который возвращает описание команды
     * @return описание команды
     */
    @Override
    public String getDescription() {
        return "выполняет команды, описанные в скрипте";
    }

    /**
     * @param commandArguments Аргументы команды.
     */
    @Override
    public void getCommandArguments(String[] commandArguments) {
        this.commandArguments = commandArguments;
    }

    /**
     * Класс, в котором хранятся адреса запущенных скриптов
     */
    static class Script {
        /**
         * Коллекция, в которой хранятся адреса запущенных скриптов
         */
        private ArrayList<String> scriptPaths = new ArrayList<String>();

        /**
         * Метод, добавляющий скрипт в коллекцию
         * @param scriptPath - адрес скрипта, который добавляется в коллекцию
         */
        public void putScript(String scriptPath) {
            scriptPaths.add(scriptPath);
        }

        /**
         * Метод, убирающий скрипт из коллекции
         * @param scriptPath - адрес скрипта, который удаляется из коллекции
         */
        public void removeScript(String scriptPath) {
            scriptPaths.remove(scriptPath);
        }
    }
}