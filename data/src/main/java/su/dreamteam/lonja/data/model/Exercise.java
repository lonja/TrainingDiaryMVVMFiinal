package su.dreamteam.lonja.data.model;

import android.support.annotation.StringRes;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
public class Exercise extends RealmObject {

    public static final int BENCH_PRESS = 0;
    public static final int SQUATS = 1;
    public static final int DEADLIFT = 2;
    public static final int BENT_OVER_ROW = 3;
    public static final int PULL_UPS = 4;

    @PrimaryKey
    private String id;

    @Index
    @StringRes
    private int title;

    private MuscleGroup group;

    private RealmList<Muscle> muscles;

    private RealmList<Muscle> synergists;

    private RealmList<Approach> approaches;

    public Exercise() {
        id = UUID.randomUUID().toString();
    }

    public Exercise(@StringRes int title, MuscleGroup group, RealmList<Muscle> muscles, RealmList<Muscle> synergists) {
        this();
        this.title = title;
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