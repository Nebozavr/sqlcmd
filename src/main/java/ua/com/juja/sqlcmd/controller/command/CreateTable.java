package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

public class CreateTable implements Command {
    public static final String CREATE_TABLE_SAMPLE = "create|tableName|column1Name fieldType|...|columnNName fieldType";

    private View view;
    private DatabaseManager manager;

    public CreateTable(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("create|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        if (data.length < 3) {
            throw new IllegalArgumentException("Error entering command, must be like " +
                    "\"" + CREATE_TABLE_SAMPLE + "\", but you enter: " + command);
        }
        String tableName = data[1];
        String columns = data[2];

        manager.createTable(tableName, columns);

        view.write(String.format("Table %s was created!", tableName));
    }
}
