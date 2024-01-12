package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class GroundTimeHoursFilterTest {

    private final GroundTimeHoursFilter groundTimeFilter = new GroundTimeHoursFilter();

    @Test
    void whenGroundTimeIsLongerThanTwoHoursThenItIsFilteredOut() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight regularFlight = new Flight(Arrays.asList(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(6))));
        Flight invalidFlight = new Flight(Arrays.asList(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6))));
        List<Flight> flights = List.of(regularFlight, invalidFlight);
        List<Flight> expected = List.of(regularFlight);
        assertThat(groundTimeFilter.filter(flights)).isEqualTo(expected);
    }

    @Test
    void whenUnsortedSegmentsThenSortedAndFilteredOut() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight regularFlight = new Flight(Arrays.asList(new Segment(threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(6)),
                new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
        Flight invalidFlight = new Flight(Arrays.asList(new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2))));
        List<Flight> flights = List.of(regularFlight, invalidFlight);
        List<Flight> expected = List.of(regularFlight);
        assertThat(groundTimeFilter.filter(flights)).isEqualTo(expected);
    }

}