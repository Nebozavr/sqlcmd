package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.controller.command.exceptions.WrongNumberParametersException;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.exceptions.PgSQLDatabaseManagerException;
import ua.com.juja.sqlcmd.view.View;

public class ClearTable implements Command {
    public static final String CLEAR_TABLE_SAMPLE = "clear|tableName";

    private final View view;
    private final DatabaseManager manager;

    public ClearTable(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("clear|");
    }

    @Override
    public void process(String command) throws WrongNumberParametersException {
        String[] data = command.split("\\|");
        if (data.length != 2) {
            throw new WrongNumberParametersException(CLEAR_TABLE_SAMPLE, command);
        }
        String tableName = data[1];

        try {
            if (!manager.hasTable(tableName)) {
                String message = String.format("Table %s doesn't exists!", tableName);
                view.write(message);
                return;

            }
            manager.clearTable(tableName);
            view.write(String.format("Table %s was cleared", tableName));
        } catch (PgSQLDatabaseManagerException e) {
            view.writeError(e);
        }
    }
}
