package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String DatabaseName;
    private String TableName;
    private String Key;

    public ReadKeyCommand(ExecutionEnvironment env, String... Args)
    {
        environment = env;

        DatabaseName = Args[0];
        TableName = Args[1];
        Key = Args[2];
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> databaseOptional = environment.getDatabase(DatabaseName);
        if (databaseOptional.isEmpty()) {
            return DatabaseCommandResult.error("Such database does not exist");
        }

        try {
            return DatabaseCommandResult.success(databaseOptional.get().read(TableName, Key));
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}