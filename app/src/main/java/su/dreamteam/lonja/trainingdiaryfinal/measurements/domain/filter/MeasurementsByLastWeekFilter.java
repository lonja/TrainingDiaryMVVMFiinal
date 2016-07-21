package su.dreamteam.lonja.trainingdiaryfinal.measurements.domain.filter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.realm.RealmResults;
import su.dreamteam.lonja.data.model.Measurement;

public class MeasurementsByLastWeekFilter implements MeasurementFilter {

    @Override
    public RealmResults<Measurement> filter(RealmResults<Measurement> measurements) {
        final GregorianCalendar calendar = new GregorianCalendar();
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentYear = calendar.get(Calendar.YEAR);
        final int firstDay = 1;
        final Date currentDate = new Date();
        final Date startOfMonthDate = new GregorianCalendar(currentYear, currentMonth, firstDay).getTime();
        return measurements.where()
                .between("date", startOfMonthDate, currentDate)
                .findAll();
    }
}
