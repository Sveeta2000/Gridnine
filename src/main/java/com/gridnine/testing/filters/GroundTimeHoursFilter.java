package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Filter that filters out flights with total ground time longer than GROUND_TIME_LIMIT_HOURS.
 */
public class GroundTimeHoursFilter implements FlightFilter {
    /**
     * The field indicates the max amount of hours allowed for ground time for one flight.
     */
    private static final int GROUND_TIME_LIMIT_HOURS = 2;

    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments().stream()
                    .sorted(Comparator.comparing(Segment::getDepartureDate))
                    .toList();
            long groundTime = 0;
            for (int i = 0; i < segments.size() - 1; i++) {
                Segment first = segments.get(i);
                Segment second = segments.get(i + 1);
                groundTime += ChronoUnit.HOURS.between(first.getArrivalDate(), second.getDepartureDate());
                if (groundTime > GROUND_TIME_LIMIT_HOURS) {
                    break;
                }
            }
            if (groundTime <= GROUND_TIME_LIMIT_HOURS) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}
