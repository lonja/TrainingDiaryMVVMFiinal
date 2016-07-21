package su.dreamteam.lonja.trainingdiaryfinal.measurements.domain.filter;


import java.util.HashMap;
import java.util.Map;

public class FilterFactory {

    private static final Map<MeasurementsFilterType, MeasurementFilter> mFilters = new HashMap<>();

    public FilterFactory() {
        mFilters.put(MeasurementsFilterType.ALL_TIME, new AllMeasurementsFilter());
        mFilters.put(MeasurementsFilterType.LAST_MONTH, new MeasurementsByLastMonthFilter());
        mFilters.put(MeasurementsFilterType.LAST_WEEK, new MeasurementsByLastWeekFilter());
    }

    public MeasurementFilter create(MeasurementsFilterType filterType) {
        return mFilters.get(filterType);
    }

}
