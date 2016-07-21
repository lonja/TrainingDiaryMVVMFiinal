package su.dreamteam.lonja.data.common.parcel;

import android.os.Parcel;

import org.parceler.Parcels;

import su.dreamteam.lonja.data.model.Approach;

public class ApproachListParcelConverter extends RealmListParcelConverter<Approach> {

    @Override
    public void itemToParcel(Approach input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public Approach itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Approach.class.getClassLoader()));
    }
}
