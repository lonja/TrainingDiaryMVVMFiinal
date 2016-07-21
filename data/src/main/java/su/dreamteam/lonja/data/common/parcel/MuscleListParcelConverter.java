package su.dreamteam.lonja.data.common.parcel;

import android.os.Parcel;

import org.parceler.Parcels;

import su.dreamteam.lonja.data.model.Muscle;

public class MuscleListParcelConverter extends RealmListParcelConverter<Muscle> {

    @Override
    public void itemToParcel(Muscle input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public Muscle itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Muscle.class.getClassLoader()));
    }
}
