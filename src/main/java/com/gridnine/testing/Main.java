package com.gridnine.testing;

import com.gridnine.testing.filters.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filters.GroundTimeHoursFilter;
import com.gridnine.testing.filters.ExcludeDepartedFlightsFilter;
import com.gridnine.testing.filters.FlightFilterContext;
import com.gridnine.testing.model.Flight;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        FlightFilterContext context = new FlightFilterContext(new ExcludeDepartedFlightsFilter());
        System.out.println("The flights that haven't departed yet : ");
        context.applyFilter(flightList).forEach(System.out::println);

        context.setFilter(new ArrivalBeforeDepartureFilter());
        System.out.println("The flights with departures preceding arrivals : ");
        context.applyFilter(flightList).forEach(System.out::println);

        context.setFilter(new GroundTimeHoursFilter());
        System.out.println("The flights with total ground time less than 2 hours : ");
        context.applyFilter(flightList).forEach(System.out::println);
    }
}
