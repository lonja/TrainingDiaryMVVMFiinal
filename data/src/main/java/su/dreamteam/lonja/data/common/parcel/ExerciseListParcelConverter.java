package su.dreamteam.lonja.data.common.parcel;

import android.os.Parcel;

import org.parceler.Parcels;

import su.dreamteam.lonja.data.model.Exercise;

public class ExerciseListParcelConverter extends RealmListParcelConverter<Exercise> {

    @Override
    public void itemToParcel(Exercise input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public Exercise itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Exercise.class.getClassLoader()));
    }
}
