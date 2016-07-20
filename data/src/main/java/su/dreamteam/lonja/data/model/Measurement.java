package su.dreamteam.lonja.data.model;

import org.parceler.Parcel;

import java.util.Date;
import java.util.UUID;

import io.realm.MeasurementRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
@Parcel(implementations = MeasurementRealmProxy.class,
        value = Parcel.Serialization.BEAN,
        analyze = Measurement.class)
public class Measurement extends RealmObject {

    @PrimaryKey
    private String id;

    @Required
    private Date date;

    private String comment;

    private Double weight;

    private Double chest;

    private Double leftShin;
    private Double rightShin;

    private Double leftHip;
    private Double rightHip;

    private Double leftBiceps;
    private Double rightBiceps;

    private Double leftForearm;
    private Double rightForearm;

    private Double waist;

    private Double neck;

    public Measurement() {
        this.id = UUID.randomUUID().toString();
        this.date = new Date();
    }

    public Measurement(Date date, String comment, Double weight, Double chest, Double leftShin,
                       Double rightShin, Double leftHip, Double rightHip, Double lefBiceps,
                       Double rightBiceps, Double leftForearm, Double rightForearm, Double waist,
                       Double neck) {
        this();
        this.date = date;
        this.comment = comment;
        this.weight = weight;
        this.chest = chest;
        this.leftShin = leftShin;
        this.rightShin = rightShin;
        this.leftHip = leftHip;
        this.rightHip = rightHip;
        this.leftBiceps = lefBiceps;
        this.rightBiceps = rightBiceps;
        this.leftForearm = leftForearm;
        this.rightForearm = rightForearm;
        this.waist = waist;
        this.neck = neck;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getChest() {
        return chest;
    }

    public void setChest(Double chest) {
        this.chest = chest;
    }

    public Double getLeftShin() {
        return leftShin;
    }

    public void setLeftShin(Double leftShin) {
        this.leftShin = leftShin;
    }

    public Double getRightShin() {
        return rightShin;
    }

    public void setRightShin(Double rightShin) {
        this.rightShin = rightShin;
    }

    public Double getLeftHip() {
        return leftHip;
    }

    public void setLeftHip(Double leftHip) {
        this.leftHip = leftHip;
    }

    public Double getRightHip() {
        return rightHip;
    }

    public void setRightHip(Double rightHip) {
        this.rightHip = rightHip;
    }

    public Double getLeftBiceps() {
        return leftBiceps;
    }

    public void setLeftBiceps(Double leftBiceps) {
        this.leftBiceps = leftBiceps;
    }

    public Double getRightBiceps() {
        return rightBiceps;
    }

    public void setRightBiceps(Double rightBiceps) {
        this.rightBiceps = rightBiceps;
    }

    public Double getLeftForearm() {
        return leftForearm;
    }

    public void setLeftForearm(Double leftForearm) {
        this.leftForearm = leftForearm;
    }

    public Double getRightForearm() {
        return rightForearm;
    }

    public void setRightForearm(Double rightForearm) {
        this.rightForearm = rightForearm;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getNeck() {
        return neck;
    }

    public void setNeck(Double neck) {
        this.neck = neck;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double findMax(Double val1, Double val2) {
        if (val1 != null && val2 != null) {
            return Math.max(val1, val2);
        } else if (val1 != null) {
            return val1;
        } else if (val2 != null) {
            return val2;
        }
        return null;
    }

    // TODO: 27.05.2016 move this to viewModel cause you have not context to find localize strings
//    @Override
//    public String toString() {
//        Double maxBiceps = findMax(leftBiceps, rightBiceps);
//        Double maxForearm = findMax(leftForearm, rightForearm);
//        Double maxHip = findMax(leftHip, rightHip);
//        Double maxShin = findMax(leftShin, rightShin);
//        StringBuilder builder = new StringBuilder();
//        builder.append("id: ")
//                .append(id)
//                .append('\n')
//                .append("Date: ")
//                .append(DateFormat.format("yyyy MMMM dd, HH:mm", date))
//                .append('\n');
//        if (comment != null) {
//            builder.append("Comment: ").append(comment).append('\n');
//        }
//        if (weight != null) {
//            builder.append("Weight: ").append(weight).append('\n');
//        }
//        if (neck != null) {
//            builder.append("Neck: ").append(neck).append('\n');
//        }
//        if (chest != null) {
//            builder.append("Chest: ").append(chest).append('\n');
//        }
//        if (maxBiceps != null) {
//            builder.append("Biceps: ").append(maxBiceps).append('\n');
//        }
//        if (maxForearm != null) {
//            builder.append("Forearm: ").append(maxForearm).append('\n');
//        }
//        if (waist != null) {
//            builder.append("Waist: ").append(waist).append('\n');
//        }
//        if (maxHip != null) {
//            builder.append("Hip : ").append(maxHip).append('\n');
//        }
//        if (maxShin != null) {
//            builder.append("Shin : ").append(maxShin).append('\n');
//        }
//        return builder.toString();
//    }

    public boolean isNotEmpty() {
        return weight != null || chest != null || leftShin != null ||
                rightShin != null || leftHip != null || rightHip != null ||
                leftBiceps != null || rightBiceps != null || leftForearm != null ||
                rightForearm != null || waist != null || neck != null;
    }
}