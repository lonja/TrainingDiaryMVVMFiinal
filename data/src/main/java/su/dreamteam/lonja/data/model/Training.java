package su.dreamteam.lonja.data.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Training extends RealmObject {

    @PrimaryKey
    private String id;

    @Required
    private Date date;

    @Required
    private Long duration;

    private RealmList<Exercise> exercises;

    public Training() {
        id = UUID.randomUUID().toString();
        date = new Date();
    }

    public Training(Date date, long duration, RealmList<Exercise> exercises) {
        this();
        this.date = date;
        this.duration = duration;
        this.exercises = exercises;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public RealmList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(RealmList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public void deleteExercise(Exercise exercise) {
        exercises.remove(exercise);
    }
}
