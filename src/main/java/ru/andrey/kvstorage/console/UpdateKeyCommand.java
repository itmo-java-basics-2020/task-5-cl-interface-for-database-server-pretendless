package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.logic.Database;
import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String DatabaseName;
    private String TableName;
    private String Key;
    private String Value;

    public UpdateKeyCommand(ExecutionEnvironment env, String... Args)
    {
        environment = env;


        DatabaseName = Args[0];
        TableName = Args[1];
        Key = Args[2];
        Value = Args[3];
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> databaseOptional = environment.getDatabase(DatabaseName);
        if (databaseOptional.isEmpty()) {
            return DatabaseCommandResult.error("Such database does not exist");
        }

        try {
            databaseOptional.get().write(TableName, Key, Value);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }

        return DatabaseCommandResult.success(Value);
    }

}

