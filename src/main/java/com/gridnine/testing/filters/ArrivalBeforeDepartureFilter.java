package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;

import java.util.List;

/**
 * Filter that filters out the flights with segments where the arrival date is earlier than the departure date.
 */
public class ArrivalBeforeDepartureFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .toList();
    }
}
