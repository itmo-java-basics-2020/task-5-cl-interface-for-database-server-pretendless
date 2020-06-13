package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.*;
import ru.andrey.kvstorage.exception.*;
import ru.andrey.kvstorage.logic.*;

import java.util.Arrays;
import java.util.Optional;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    DatabaseCommandResult executeNextCommand(String commandText) {

        if(commandText == null){
            return DatabaseCommandResult.error("The given command is null");
        }

        String[] Args = commandText.split(" ");
        DatabaseCommandENUM SuitableCommand;
        try {
            SuitableCommand = DatabaseCommandENUM.valueOf(Args[0]);
        }
        catch (IllegalArgumentException exc) {
            return DatabaseCommandResult.error("Such command does not exist");
        }
        DatabaseCommand command = SuitableCommand.getCommand(env, Arrays.copyOfRange(Args, 1, Args.length));

        return tryExecute(command);
    }

    private DatabaseCommandResult tryExecute(DatabaseCommand command)
    {
        try {
            return command.execute();
        }
        catch (IllegalArgumentException | DatabaseException exc){
            return DatabaseCommandResult.error("This command can't be executed");
        }
    }
}
