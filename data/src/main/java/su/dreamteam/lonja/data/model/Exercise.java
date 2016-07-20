package su.dreamteam.lonja.data.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Exercise extends RealmObject {

    public static final int BENCH_PRESS = 0;
    public static final int SQUATS = 1;
    public static final int DEADLIFT = 2;
    public static final int BENT_OVER_ROW = 3;
    public static final int PULL_UPS = 4;

    @PrimaryKey
    private String id;

    @DrawableRes
    private int icon;

    @DrawableRes
    private int image;

    @StringRes
    private int title;

    @StringRes
    private int description;

    @StringRes
    private int technique;

    private MuscleGroup group;

    private RealmList<Muscle> muscles;

    private RealmList<Muscle> synergists;

    private RealmList<Approach> approaches;

    public Exercise() {
        id = UUID.randomUUID().toString();
    }

    public Exercise(@StringRes int title, @DrawableRes int icon,
                    @DrawableRes int image, @StringRes int description,
                    @StringRes int technique, MuscleGroup group,
                    RealmList<Muscle> muscles, RealmList<Muscle> synergists) {
        this();
        this.title = title;
        this.icon = icon;
        this.image = image;
        this.description = description;
        this.technique = technique;
        this.group = group;
        this.muscles = muscles;
        this.synergists = synergists;
        approaches = new RealmList<>();
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getTechnique() {
        return technique;
    }

    public void setTechnique(int technique) {
        this.technique = technique;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public MuscleGroup getGroup() {
        return group;
    }

    public void setGroup(MuscleGroup group) {
        this.group = group;
    }

    public RealmList<Muscle> getMuscles() {
        return muscles;
    }

    public void setMuscles(RealmList<Muscle> muscles) {
        this.muscles = muscles;
    }

    public RealmList<Muscle> getSynergists() {
        return synergists;
    }

    public void setSynergists(RealmList<Muscle> synergists) {
        this.synergists = synergists;
    }

    public void addApproach(Approach approach) {
        approaches.add(approach);
    }

    public void deleteApproach(Approach approach) {
        approaches.remove(approach);
    }

    public RealmList<Approach> getApproaches() {
        return approaches;
    }

    public void setApproaches(RealmList<Approach> approaches) {
        this.approaches = approaches;
    }
}