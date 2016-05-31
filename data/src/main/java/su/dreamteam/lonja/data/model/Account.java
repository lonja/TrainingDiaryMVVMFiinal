package su.dreamteam.lonja.data.model;

import org.parceler.Parcel;

import java.util.Date;
import java.util.UUID;

import io.realm.AccountRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

@RealmClass
@Parcel(implementations = AccountRealmProxy.class,
        value = Parcel.Serialization.FIELD,
        analyze = Account.class)
public class Account extends RealmObject {

    @PrimaryKey
    private String id;

    @Required
    private String name;

    @Required
    private String sex;

    @Required
    private Date birthDate;

    @Required
    private Double height;

    @Required
    private Double weight;

    public Account() {
        id = UUID.randomUUID().toString();
        sex = "male";
    }

    public Account(String name, String sex, Date birthDate, double height,
                   double weight) {
        this();
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
