package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.Database;
import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.Optional;


public class CreateTableCommand implements DatabaseCommand {

    private ExecutionEnvironment environment;
    private String DatabaseName;
    private String TableName;

    public CreateTableCommand(ExecutionEnvironment env, String[] args) {
        environment = env;

        DatabaseName = args[0];
        TableName = args[1];
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> databaseOptional = environment.getDatabase(DatabaseName);
        if (databaseOptional.isEmpty()) {
            return DatabaseCommandResult.error("Such database does not exist");
        }

        try {
            databaseOptional.get().createTableIfNotExists(TableName);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }

        return DatabaseCommandResult.success("Table was created successfully");
    }

}
