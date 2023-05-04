package org.example.Commands;

import org.example.FileWork.HumanBeingReader;
import org.example.data.CollectionManager;
import org.example.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс, через который осуществляется работа команд
 */
public class CommandManager {
    /**
     * Коллекция команд без аргументов
     */
    private HashMap<String, Command> commandsWithoutArguments;
    /**
     * Коллекция команд с аргументами
     */
    private HashMap<String, CommandWithArguments> commandsWithArguments;
    /**
     * Ссылка на объект класса CollectionManager
     */
    private CollectionManager collectionManager;
    /**
     * Ссылка на объект класса User
     */
    private User user;
    /**
     * Поле, хранящее путь к файлу
     */
    private String inputFile;
    /**
     * Поле, хранящее ссылку на объект HumanBeingReader
     */
    private HumanBeingReader humanBeingReader;
    /**
     * Объект класса ExecuteScript.Script
     */
    ExecuteScriptCommand.Script script;

    /**
     * Конструктор класса
     * Внутри вызывается метод putCommands,
     * добавляющий команды в коллекции команд,
     * создается новый объект класса ExecuteScript. Script
     * @param collectionManager  Хранит ссылку созданный в объекте Application объект CollectionManager.
     * @param user               Хранит ссылку на объект класса UserIO.
     * @param inputFile          Хранит строку, в которой записан адрес файла, куда следует сохранять полученную коллекцию (экземпляры коллекции).
     * @param humanBeingReader   Хранит ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     */
    public CommandManager(CollectionManager collectionManager,
                          User user,
                          String inputFile,
                          HumanBeingReader humanBeingReader) {
        this.collectionManager = collectionManager;
        this.user = user;
        this.inputFile = inputFile;
        this.humanBeingReader = humanBeingReader;

        commandsWithoutArguments = new HashMap<>();
        commandsWithArguments = new HashMap<>();
        this.script = new ExecuteScriptCommand.Script();
        this.putCommands();
    }

    /**
     * Конструктор класса
     * Внутри вызывается метод putCommands,
     * инициализируется поле,
     * в которое присваивается существующий объект класса ExecuteScript.Script
     * @param collectionManager  Хранит ссылку на созданный в объекте Application объект CollectionManager.
     * @param user               Хранит ссылку на объект класса UserIO.
     * @param humanBeingReader   Хранит ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     * @param script             Хранит ссылку на объект класса ExecuteScript.Script.
     */
    public CommandManager(CollectionManager collectionManager,
                          User user,
                          HumanBeingReader humanBeingReader,
                          ExecuteScriptCommand.Script script) {
        this.collectionManager = collectionManager;
        this.user = user;
        this.humanBeingReader = humanBeingReader;

        commandsWithoutArguments = new HashMap<>();
        commandsWithArguments = new HashMap<>();
        this.script = script;
        this.putCommands();
    }

    /**
     * Метод добавляет команды в коллекцию
     */
    private void putCommands() {
        // команды без аргумента
        commandsWithoutArguments.put("info", new InfoCommand(collectionManager));
        commandsWithoutArguments.put("show", new ShowCommand(collectionManager));
        commandsWithoutArguments.put("eric", new EricCommand());
        commandsWithoutArguments.put("clear", new ClearCommand(collectionManager));
        commandsWithoutArguments.put("save", new SaveCommand(collectionManager));
        commandsWithoutArguments.put("exit", new ExitCommand());
        commandsWithoutArguments.put("help", new HelpCommand(commandsWithoutArguments, commandsWithArguments));
        commandsWithoutArguments.put("add", new AddCommand(collectionManager, humanBeingReader));
        commandsWithoutArguments.put("add_if_max", new AddIfMaxCommand(humanBeingReader, collectionManager));
        commandsWithoutArguments.put("add_if_min", new AddIfMinCommand(humanBeingReader, collectionManager));
        commandsWithoutArguments.put("sum_of_minutes_of_waiting", new SumOfMinutesOfWaiting(collectionManager));
        commandsWithoutArguments.put("unique_minutes_of_waiting", new UniqueMinutesOfWaitingCommand(collectionManager));
        //команды с аргументом
        commandsWithArguments.put("count_by_soundtrack_name", new CountBySoundtrackNameCommand(collectionManager));
        commandsWithArguments.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commandsWithArguments.put("remove_by_id", new RemoveByIDCommand(collectionManager));
        commandsWithArguments.put("update_id", new UpdateIDCommand(humanBeingReader, collectionManager));
        commandsWithArguments.put("execute_script", new ExecuteScriptCommand(collectionManager, humanBeingReader, script));
    }

    /**
     * Метод, который определяет команду из строки,
     * исполняет ее и передает ей аргументы
     * Если команда не распознана передает сообщение об этом
     *
     * @param firstCommandLine первая строка команды, где хранится ее название и переданный аргумент
     */
    public void execute(String firstCommandLine) {
        String[] words = firstCommandLine.trim().split("\\s");
        String[] args = Arrays.copyOfRange(words, 1, words.length);

        if (commandsWithArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            CommandWithArguments command;

            command = commandsWithArguments.get(words[0].toLowerCase(Locale.ROOT));
            command.getCommandArguments(args);
            command.execute();
        } else if (commandsWithoutArguments.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            Command command;

            command = commandsWithoutArguments.get(words[0].toLowerCase(Locale.ROOT));
            command.execute();
        } else {
            System.err.println("команда " + words[0] + " не распознана");
        }
    }
}