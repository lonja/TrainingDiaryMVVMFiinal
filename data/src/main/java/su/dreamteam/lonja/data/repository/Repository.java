package su.dreamteam.lonja.data.repository;

public abstract class Repository {

    protected Repository() {

    }

    abstract void closeDatabaseConnection();
}