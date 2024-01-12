package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;

import java.util.List;

public class FlightFilterContext {
    private FlightFilter flightFilter;

    public FlightFilterContext(FlightFilter flightFilter) {
        this.flightFilter = flightFilter;
    }

    public void setFilter(FlightFilter flightFilter) {
        this.flightFilter = flightFilter;
    }

    public List<Flight> applyFilter(List<Flight> flights) {
        return flightFilter.filter(flights);
    }
}
