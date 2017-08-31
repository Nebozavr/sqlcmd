package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.controller.command.*;
import ua.com.juja.sqlcmd.controller.command.exceptions.ExitException;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class MainController {

    private Command[] commands;
    private View view;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.commands = new Command[]{
                new Exit(view),
                new Help(view),
                new Connect(view, manager),
                new isConnected(view, manager),
                new ListTables(view, manager),
                new FindData(view, manager),
                new CreateTable(view, manager),
                new DropTable(view, manager),
                new ClearTable(view, manager),
                new InsertData(view, manager),
                new DeleteRows(view, manager),
                new UpdateData(view, manager),
                new UnknownCommand(view)};
    }

    public void run() {
        try {
            doWork();
        } catch (ExitException e) {

        }
    }

    private void doWork() {
        view.write("Hello User");
        view.write("Please enter database name, username and password, " +
                      "in the format: " + Connect.CONNECT_SAMPLE);

        while (true) {
            String input = view.read();

            for (Command command : commands) {
                try {
                    if (command.canProcess(input)) {
                        command.process(input);
                        break;
                    }
                } catch (Exception e) {
                    if (e instanceof ExitException) {
                        throw e;
                    }

                    printError(e);
                    break;
                }
            }
            view.write("Enter a new command or use help command.");
        }
    }

    private void printError(Exception e) {
        String message = e.getMessage();
        if (e.getCause() != null && e.getCause().equals(e.getMessage())) {
            message += " " + e.getCause().getMessage();
        }
        view.write("An error occurred because: " + message);
        view.write("Please try again");
    }


}
