package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.controller.command.exceptions.WrongNumberParametersException;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.exceptions.PgSQLDatabaseManagerException;
import ua.com.juja.sqlcmd.view.View;

public class DropTable implements Command {
    public static final String DROP_TABLE_SAMPLE = "drop|tableName";

    private final View view;
    private final DatabaseManager manager;

    public DropTable(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("drop|");
    }

    @Override
    public void process(String command) throws WrongNumberParametersException {
        String[] data = command.split("\\|");
        if (data.length != 2) {
            throw new WrongNumberParametersException(DROP_TABLE_SAMPLE, command);
        }

        String tableName = data[1];

        try {
            if (!manager.hasTable(tableName)) {
                String message = String.format("Table %s doesn't exists!", tableName);
                view.write(message);
                return;
            }
            manager.dropTable(tableName);
            view.write(String.format("Table %s was delete", tableName));
        } catch (PgSQLDatabaseManagerException e) {
            view.writeError(e);
        }
    }
}
