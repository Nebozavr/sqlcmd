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
        view.write("\t create|tableName|column1Name fieldType|...|columnNName fieldType| \r\n\t\t Create new table");
        view.write("\t drop|tableName \r\n\t\t Delete table");
        view.write("\t clear|tableName \r\n\t\t Clear all data from table");
        view.write("\t insert|tableName|columnName1|value1|...|columnNameN|valueN \r\n\t\t Insert new data to table");
        view.write("\t delete|tableName|columnName|value \r\n\t\t Delete data from table");
        view.write("\t update|tableName|columnNameSet|valueSet|columnNameWhere|valueWhere \r\n\t\t Update data from table");
        view.write("\t exit \r\n\t\t Close connection to database and exit program!");
    }
}
