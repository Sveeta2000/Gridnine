package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ExcludeDepartedFlightsFilterTest {

    private final ExcludeDepartedFlightsFilter departureFilter = new ExcludeDepartedFlightsFilter();

    @Test
    void whenDepartureIsBeforeNowThenItIsFilteredOut() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight regularFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
        Flight invalidFlight = new Flight(List.of(new Segment(threeDaysFromNow.minusDays(6), threeDaysFromNow)));
        List<Flight> flights = List.of(regularFlight, invalidFlight);
        List<Flight> expected = List.of(regularFlight);
        assertThat(departureFilter.filter(flights)).isEqualTo(expected);
    }

    @Test
    void whenDepartureIsBeforeNowInOneSegmentThenItIsFilteredOut() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight regularFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5))));
        Flight invalidFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.minusDays(4), threeDaysFromNow)));
        List<Flight> flights = List.of(regularFlight, invalidFlight);
        List<Flight> expected = List.of(regularFlight);
        assertThat(departureFilter.filter(flights)).isEqualTo(expected);
    }
}