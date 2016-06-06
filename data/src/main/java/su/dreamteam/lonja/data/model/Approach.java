package su.dreamteam.lonja.data.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
public class Approach extends RealmObject {

    @PrimaryKey
    private String id;

    private Exercise exercise;

    @Required
    private Integer reps;

    @Required
    private Integer weight;

    public Approach() {
        id = UUID.randomUUID().toString();
    }

    public Approach(Exercise exercise, Integer reps, Integer weight) {
        this();
        this.exercise = exercise;
        this.reps = reps;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
