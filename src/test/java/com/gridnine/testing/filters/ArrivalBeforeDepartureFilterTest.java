package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ArrivalBeforeDepartureFilterTest {

    private final ArrivalBeforeDepartureFilter arrivalFilter = new ArrivalBeforeDepartureFilter();

    @Test
    void whenArrivalIsBeforeDepartureThenItIsFilteredOut() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight regularFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
        Flight invalidFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6))));
        List<Flight> flights = List.of(regularFlight, invalidFlight);
        List<Flight> expected = List.of(regularFlight);
        assertThat(arrivalFilter.filter(flights)).isEqualTo(expected);
    }

    @Test
    void whenArrivalEqualsDepartureThenItIsFilteredOut() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight regularFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
        Flight invalidFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow)));
        List<Flight> flights = List.of(regularFlight, invalidFlight);
        List<Flight> expected = List.of(regularFlight);
        assertThat(arrivalFilter.filter(flights)).isEqualTo(expected);
    }

    @Test
    void whenArrivalBeforeDepartureInOneSegmentThenItIsFilteredOut() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight regularFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
        Flight invalidFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow)));
        List<Flight> flights = List.of(regularFlight, invalidFlight);
        List<Flight> expected = List.of(regularFlight);
        assertThat(arrivalFilter.filter(flights)).isEqualTo(expected);
    }

}