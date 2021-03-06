package su.dreamteam.lonja.data.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.UUID;

import io.realm.MuscleGroupRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import su.dreamteam.lonja.data.common.parcel.MuscleListParcelConverter;

@RealmClass
@Parcel(implementations = MuscleGroupRealmProxy.class,
        value = Parcel.Serialization.BEAN,
        analyze = MuscleGroup.class)
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

    @StringRes
    private int title;

    private RealmList<Muscle> muscles;

    @DrawableRes
    private int imageDrawableRes;

    @StringRes
    private int description;

    public MuscleGroup() {
        id = UUID.randomUUID().toString();
    }

    public MuscleGroup(@StringRes int title, RealmList<Muscle> muscles, int imageDrawableRes, int description) {
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

    public int getTitle() {
        return title;
    }

    public void setTitle(@StringRes int title) {
        this.title = title;
    }

    public RealmList<Muscle> getMuscles() {
        return muscles;
    }

    @ParcelPropertyConverter(MuscleListParcelConverter.class)
    public void setMuscles(RealmList<Muscle> muscles) {
        this.muscles = muscles;
    }

    public int getImageDrawableRes() {
        return imageDrawableRes;
    }

    public void setImageDrawableRes(int imageDrawableRes) {
        this.imageDrawableRes = imageDrawableRes;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(@StringRes int description) {
        this.description = description;
    }
}
