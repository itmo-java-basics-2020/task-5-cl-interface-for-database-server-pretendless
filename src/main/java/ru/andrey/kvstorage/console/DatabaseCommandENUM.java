package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public enum DatabaseCommandENUM {

    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
        {
            if(args.length != 1)
                return () -> DatabaseCommandResult.error("Not correct amount of arguments");

            return new CreateDatabaseCommand(env, args);
        }
    },

    CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
        {
            if(args.length != 2)
                return () -> DatabaseCommandResult.error("Not correct amount of arguments");

            return new CreateTableCommand(env, args);
        }
    },

    UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
        {
            if(args.length != 4)
                return () -> DatabaseCommandResult.error("Not correct amount of arguments");

            return new UpdateKeyCommand(env, args);
        }
    },

    READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args)
        {
            if(args.length != 3)
                return () -> DatabaseCommandResult.error("Not correct amount of arguments");

            return new ReadKeyCommand(env, args);
        }
    };

    public abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args);
}


