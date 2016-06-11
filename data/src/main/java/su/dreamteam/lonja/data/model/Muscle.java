package su.dreamteam.lonja.data.model;

import android.support.annotation.StringRes;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Muscle extends RealmObject {

    public static final int TRAPEZIUS = 0;
    public static final int BRACHIALIS = 1;
    public static final int BICEPS_LONG_HEAD = 2;
    public static final int BICEPS_SHORT_HEAD = 3;
    public static final int PECTORALIS_MINOR = 4;
    public static final int PECTORALIS_MAJOR = 5;
    public static final int TRICEPS_LATERAL_HEAD = 6;
    public static final int TRICEPS_LONG_HEAD = 7;
    public static final int TRICEPS_MEDIAL_HEAD = 8;
    public static final int SHOULDER_ANTERIOR_HEAD = 9;
    public static final int SHOULDER_MIDDLE_HEAD = 10;
    public static final int SHOULDER_POSTERIOR_HEAD = 11;

    @PrimaryKey
    private String id;

    @Required
    @StringRes
    private int title;

    private MuscleGroup muscleGroup;

    private RealmList<Exercise> exercises;

    public Muscle() {
        id = UUID.randomUUID().toString();
    }

    public Muscle(@StringRes int title, MuscleGroup muscleGroup, RealmList<Exercise> exercises) {
        this();
        this.title = title;
        this.muscleGroup = muscleGroup;
        this.exercises = exercises;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(@StringRes int title) {
        this.title = title;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public RealmList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(RealmList<Exercise> exercises) {
        this.exercises = exercises;
    }
}