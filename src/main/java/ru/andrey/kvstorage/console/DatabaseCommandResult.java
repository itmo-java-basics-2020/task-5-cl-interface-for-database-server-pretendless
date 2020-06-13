package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    static DatabaseCommandResult success(String result)
    {
        return new DatabaseCommandResultClass(DatabaseCommandStatus.SUCCESS, result);
    }

    static DatabaseCommandResult error(String result)
    {
        return new DatabaseCommandResultClass(DatabaseCommandStatus.FAILED, result);
    }

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }
}

class DatabaseCommandResultClass implements DatabaseCommandResult {
    private final DatabaseCommandStatus status;
    private final String result;

    DatabaseCommandResultClass(DatabaseCommandStatus status, String result){
        this.status = status;
        this.result = result;
    }

    @Override
    public Optional<String> getResult() {
        if(status == DatabaseCommandStatus.SUCCESS) {
            return Optional.of(result);
        }

        return Optional.empty();
    }

    @Override
    public DatabaseCommandStatus getStatus() {
        return status;
    }

    @Override
    public boolean isSuccess() {
        return status == DatabaseCommandStatus.SUCCESS;
    }

    @Override
    public String getErrorMessage() {
        if(status == DatabaseCommandStatus.FAILED) {
            return result;
        }
        return null;
    }
}


