package su.dreamteam.lonja.trainingdiaryfinal;

import android.app.Application;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import su.dreamteam.lonja.data.RealmHelper;
import su.dreamteam.lonja.data.model.Exercise;
import su.dreamteam.lonja.data.model.Muscle;
import su.dreamteam.lonja.data.model.MuscleGroup;

public class TrainingDiaryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmHelper.getInstance(this, getInitialData());
    }

    private Realm.Transaction getInitialData() {
        return realm -> {
            MuscleGroup biceps = realm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
            biceps.setImageDrawableRes(R.drawable.biceps);
            biceps.setTitle(R.string.biceps);
            biceps.setDescription(R.string.biceps_description);

            Muscle brachialis = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            brachialis.setMuscleGroup(biceps);
            brachialis.setTitle(R.string.brachialis);

            Muscle bicepsLongHead = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            bicepsLongHead.setMuscleGroup(biceps);
            bicepsLongHead.setTitle(R.string.biceps_long_head);

            Muscle bicepsShortHead = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            bicepsShortHead.setMuscleGroup(biceps);
            bicepsShortHead.setTitle(R.string.biceps_short_head);

            RealmList<Muscle> bicepsMuscles = new RealmList<>(
                    brachialis,
                    bicepsLongHead,
                    bicepsShortHead);
            biceps.setMuscles(bicepsMuscles);


            MuscleGroup triceps = realm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
            triceps.setImageDrawableRes(R.drawable.triceps);
            triceps.setTitle(R.string.triceps);
            triceps.setDescription(R.string.triceps_description);

            Muscle tricepsLateralHead = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            tricepsLateralHead.setMuscleGroup(triceps);
            tricepsLateralHead.setTitle(R.string.triceps_lateral_head);

            Muscle tricepsLongHead = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            tricepsLongHead.setMuscleGroup(triceps);
            tricepsLongHead.setTitle(R.string.triceps_long_head);

            Muscle tricepsMedialHead = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            tricepsMedialHead.setMuscleGroup(triceps);
            tricepsMedialHead.setTitle(R.string.triceps_medial_head);

            RealmList<Muscle> tricepsMuscles = new RealmList<>(
                    tricepsLateralHead,
                    tricepsLongHead,
                    tricepsMedialHead);
            triceps.setMuscles(tricepsMuscles);


            MuscleGroup chest = realm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
            chest.setImageDrawableRes(R.drawable.chest);
            chest.setTitle(R.string.chest);
            chest.setDescription(R.string.chest_description);

            Muscle pectoralisMinor = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            pectoralisMinor.setMuscleGroup(chest);
            pectoralisMinor.setTitle(R.string.pectoralis_minor);

            Muscle pectoralisMajor = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            pectoralisMajor.setMuscleGroup(chest);
            pectoralisMajor.setTitle(R.string.pectoralis_major);

            RealmList<Muscle> chestMuscles = new RealmList<>(
                    pectoralisMajor,
                    pectoralisMinor);
            chest.setMuscles(chestMuscles);


            MuscleGroup shoulder = realm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
            shoulder.setImageDrawableRes(R.drawable.shoulders);
            shoulder.setTitle(R.string.shoulders);
            shoulder.setDescription(R.string.shoulders_description);

            Muscle shoulderAnteriorHead = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            shoulderAnteriorHead.setMuscleGroup(shoulder);
            shoulderAnteriorHead.setTitle(R.string.shoulder_anterior_head);

            Muscle shoulderMiddleHead = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            shoulderMiddleHead.setMuscleGroup(shoulder);
            shoulderMiddleHead.setTitle(R.string.shoulder_middle_head);

            Muscle shoulderPosteriorHead = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            shoulderPosteriorHead.setMuscleGroup(shoulder);
            shoulderPosteriorHead.setTitle(R.string.shoulder_posterior_head);

            RealmList<Muscle> shoulderMuscles = new RealmList<>(
                    shoulderAnteriorHead,
                    shoulderMiddleHead,
                    shoulderPosteriorHead);
            shoulder.setMuscles(shoulderMuscles);


            MuscleGroup back = realm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
            back.setImageDrawableRes(R.drawable.back);
            back.setTitle(R.string.back);
            back.setDescription(R.string.back_description);

            Muscle trapezius = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            trapezius.setMuscleGroup(back);
            trapezius.setTitle(R.string.trapezius);

            Muscle erectorSpinae = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            erectorSpinae.setMuscleGroup(back);
            erectorSpinae.setTitle(R.string.erector_spinae);

            Muscle latissimusDorsi = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            latissimusDorsi.setMuscleGroup(back);
            latissimusDorsi.setTitle(R.string.latissimus_dorsi);

            Muscle teresMajor = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            teresMajor.setMuscleGroup(back);
            teresMajor.setTitle(R.string.teres_major);

            Muscle teresMinor = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            teresMinor.setMuscleGroup(back);
            teresMinor.setTitle(R.string.teres_minor);

            Muscle infraspinatus = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            infraspinatus.setMuscleGroup(back);
            infraspinatus.setTitle(R.string.infraspinatus);

            Muscle rhomboidMajor = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            rhomboidMajor.setMuscleGroup(back);
            rhomboidMajor.setTitle(R.string.rhomboid_major);

            RealmList<Muscle> backMuscles = new RealmList<>(
                    trapezius,
                    erectorSpinae,
                    latissimusDorsi,
                    teresMajor,
                    teresMinor,
                    infraspinatus,
                    rhomboidMajor
            );
            shoulder.setMuscles(backMuscles);

            MuscleGroup thighs = realm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
            thighs.setImageDrawableRes(R.drawable.thighs);
            thighs.setTitle(R.string.thigs);
            thighs.setDescription(R.string.thighs_description);

            Muscle quadriceps = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            quadriceps.setTitle(R.string.quadriceps);
            quadriceps.setMuscleGroup(thighs);

            RealmList<Muscle> thighsMuscles = new RealmList<>(quadriceps);
            thighs.setMuscles(thighsMuscles);

            MuscleGroup hamstrings = realm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
            hamstrings.setImageDrawableRes(R.drawable.hamstrings);
            hamstrings.setTitle(R.string.hamstrings);
            hamstrings.setDescription(R.string.hamstrings_description);

            Muscle bicepsFemoris = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            bicepsFemoris.setTitle(R.string.biceps_femoris);
            bicepsFemoris.setMuscleGroup(hamstrings);

            Muscle semitendinosus = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            semitendinosus.setTitle(R.string.semitendinosus);
            semitendinosus.setMuscleGroup(hamstrings);

            Muscle semimembranosus = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            semimembranosus.setTitle(R.string.semimembranosus);
            semimembranosus.setMuscleGroup(hamstrings);

            RealmList<Muscle> hamstringsMuscles = new RealmList<>(
                    bicepsFemoris,
                    semimembranosus,
                    semitendinosus
            );
            hamstrings.setMuscles(hamstringsMuscles);


            MuscleGroup glutes = realm.createObject(MuscleGroup.class, UUID.randomUUID().toString());
            glutes.setImageDrawableRes(R.drawable.glutes);
            glutes.setTitle(R.string.glutes);
            glutes.setDescription(R.string.glutes_description);

            Muscle gluteusMaximus = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            gluteusMaximus.setTitle(R.string.gluteus_maximus);
            gluteusMaximus.setMuscleGroup(glutes);

            Muscle gluteusMedius = realm.createObject(Muscle.class, UUID.randomUUID().toString());
            gluteusMedius.setTitle(R.string.gluteus_medius);
            gluteusMedius.setMuscleGroup(glutes);

            RealmList<Muscle> glutesMuscles = new RealmList<>(
                    gluteusMaximus,
                    gluteusMedius
            );
            glutes.setMuscles(glutesMuscles);


            Exercise benchPress = realm.createObject(Exercise.class, UUID.randomUUID().toString());
            RealmList<Muscle> benchMuscles = new RealmList<>(
                    pectoralisMajor,
                    shoulderAnteriorHead,
                    tricepsLateralHead,
                    tricepsLongHead,
                    tricepsMedialHead,
                    brachialis);
            RealmList<Muscle> benchSynergistsMuscles = new RealmList<>(
                    trapezius);
            benchPress.setTitle(R.string.bench_press);
            benchPress.setMuscles(benchMuscles);
            benchPress.setSynergists(benchSynergistsMuscles);
            benchPress.setGroup(chest);
            benchPress.setIcon(R.drawable.ic_bench_press);
            benchPress.setImage(R.drawable.bench_press);

            Exercise squats = realm.createObject(Exercise.class, UUID.randomUUID().toString());
            RealmList<Muscle> squatsMuscles = new RealmList<>(
                    quadriceps,
                    gluteusMaximus
            );
            RealmList<Muscle> squatsSynergistsMuscles = new RealmList<>(
                    gluteusMedius,
                    semimembranosus,
                    semitendinosus,
                    bicepsFemoris,
                    erectorSpinae
            );
            squats.setTitle(R.string.squats);
            squats.setMuscles(squatsMuscles);
            squats.setSynergists(squatsSynergistsMuscles);
            squats.setGroup(thighs);
            squats.setIcon(R.drawable.ic_squats);
            squats.setImage(R.drawable.squats);

            Exercise deadLift = realm.createObject(Exercise.class, UUID.randomUUID().toString());
            RealmList<Muscle> deadLiftMuscles = new RealmList<>(
                    erectorSpinae,
                    bicepsFemoris,
                    gluteusMaximus,
                    gluteusMedius,
                    trapezius
            );
            RealmList<Muscle> deadLiftSynergistsMuscles = new RealmList<>(
                    quadriceps,
                    bicepsLongHead,
                    bicepsShortHead
            );
            deadLift.setTitle(R.string.deadlift);
            deadLift.setMuscles(deadLiftMuscles);
            deadLift.setSynergists(deadLiftSynergistsMuscles);
            deadLift.setGroup(back);
            deadLift.setIcon(R.drawable.ic_deadlift);
            deadLift.setImage(R.drawable.deadlift);

            Exercise pullUps = realm.createObject(Exercise.class, UUID.randomUUID().toString());
            RealmList<Muscle> pullUpsMuscles = new RealmList<>(
                    latissimusDorsi,
                    brachialis,
                    bicepsLongHead,
                    bicepsShortHead,
                    teresMajor,
                    rhomboidMajor
            );
            RealmList<Muscle> pullUpsSynergistsMuscles = new RealmList<>(
                    shoulderPosteriorHead,
                    erectorSpinae,
                    tricepsLongHead
            );
            pullUps.setTitle(R.string.pull_ups);
            pullUps.setMuscles(pullUpsMuscles);
            pullUps.setSynergists(pullUpsSynergistsMuscles);
            pullUps.setGroup(back);
            pullUps.setDescription(R.string.pull_ups_description);
            pullUps.setTechnique(R.string.pull_ups_technique);
            pullUps.setIcon(R.drawable.ic_pull_ups);
            pullUps.setImage(R.drawable.pull_ups);
        };

    }

}