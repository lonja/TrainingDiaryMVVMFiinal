package su.dreamteam.lonja.data.model;

import android.support.annotation.DrawableRes;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class MuscleGroup extends RealmObject {

    public static final int BACK = 0;
    public static final int CHEST = 1;
    public static final int BICEPS = 2;
    public static final int TRICEPS = 3;
    public static final int SHOULDER = 4;
    public static final int ABS = 5;
    public static final int FOREARM = 6;
    public static final int CALVES = 7;
    public static final int THIGHS = 8;
    public static final int GLUTES = 9;

    @PrimaryKey
    private String id;

    @Required
    private String title;

    private RealmList<Muscle> muscles;

    @DrawableRes
    private int imageDrawableRes;

    private String description;

    public MuscleGroup() {
        id = UUID.randomUUID().toString();
    }

    public MuscleGroup(String title, RealmList<Muscle> muscles, int imageDrawableRes, String description) {
        this();
        this.title = title;
        this.muscles = muscles;
        this.imageDrawableRes = imageDrawableRes;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<Muscle> getMuscles() {
        return muscles;
    }

    public void setMuscles(RealmList<Muscle> muscles) {
        this.muscles = muscles;
    }

    public int getImageDrawableRes() {
        return imageDrawableRes;
    }

    public void setImageDrawableRes(int imageDrawableRes) {
        this.imageDrawableRes = imageDrawableRes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
