package ru.andrey.kvstorage.console;

public class CreateDatabaseCommand implements DatabaseCommand {

    private ExecutionEnvironment environment;
    private String DatabaseName;

    public CreateDatabaseCommand(ExecutionEnvironment env, String... args) {
        environment = env;
        if(args.length != 1)
            throw new IllegalArgumentException("Not enough/Too much arguments given");

        DatabaseName = args[0];
    }

    @Override
    public DatabaseCommandResult execute() {

        environment.addDatabase(null);
        return DatabaseCommandResult.success("Database created");
    }

}