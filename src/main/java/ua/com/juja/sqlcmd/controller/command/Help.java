package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.view.View;

public class Help implements Command {

    private View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("help");
    }

    @Override
    public void process(String command) {
        view.write("List of all commands:");
        view.write("\t connect|database|username|password \r\n\t\t Connect to database");
        view.write("\t help \r\n\t\t View all commands and their description");
        view.write("\t list \r\n\t\t Show all tables from database");
        view.write("\t find|tableName \r\n\t\t Show all data from tableName");
        view.write("\t exit \r\n\t\t Close connection to database and exit program!");
    }
}
