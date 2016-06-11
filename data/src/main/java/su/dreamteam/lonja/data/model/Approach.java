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

    private String comment;

    @Required
    private Integer reps;

    @Required
    private Integer weight;

    private Training training;

    public Approach() {
        id = UUID.randomUUID().toString();
    }

    public Approach(Exercise exercise, String comment, Integer reps, Integer weight, Training training) {
        this();
        this.exercise = exercise;
        this.comment = comment;
        this.reps = reps;
        this.weight = weight;
        this.training = training;
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

    public Training getTraining() {
        return training;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
