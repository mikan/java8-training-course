/*
 * Copyright (c) 2015. Yutaka Kato. All rights reserved.
 * https://github.com/mikan/java8-training-course
 */

package local.js8ri.ch05.ex10;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

import static org.junit.Assert.assertEquals;

/**
 * @author mikan
 */
public class ArrivalTimeTest {

    @Test(expected = NullPointerException.class)
    public void testArrivalTime_NPE() {
        ArrivalTime.arrivalTime(null, null, null, null);
    }

    @Test
    public void testArrivalTime_LH457() {
        // Lufthansa LH457 (LAX 8/01 15:10 -> FRA 8/02 11:10, Duration: 11:00)
        LocalDateTime departureTime = LocalDateTime.of(2015, Month.AUGUST, 1, 15, 10);
        ZoneId departureZone = ZoneId.of("America/Los_Angeles");
        Duration flightTime = ArrivalTime.createHMDuration(11, 0);
        ZoneId arrivalZone = ZoneId.of("Europe/Berlin");
        LocalDateTime arrivalTime = ArrivalTime.arrivalTime(departureTime, departureZone, flightTime, arrivalZone);
        assertEquals(LocalDateTime.of(2015, Month.AUGUST, 2, 11, 10), arrivalTime);
    }

    @Test
    public void testArrivalTime_JL080() {
        // Japan Airlines JL080 (HND 8/01 23:40 -> HNL 8/01 12:15, Duration: 7:35)
        LocalDateTime departureTime = LocalDateTime.of(2015, Month.AUGUST, 1, 23, 40);
        ZoneId departureZone = ZoneId.of("Asia/Tokyo");
        Duration flightTime = ArrivalTime.createHMDuration(7, 35);
        ZoneId arrivalZone = ZoneId.of("US/Hawaii");
        LocalDateTime arrivalTime = ArrivalTime.arrivalTime(departureTime, departureZone, flightTime, arrivalZone);
        assertEquals(LocalDateTime.of(2015, Month.AUGUST, 1, 12, 15), arrivalTime);
    }

    @Test
    public void testArrivalTime_CV6356() {
        // Cargolux CV6356 (KMQ 8/01 16:40 -> LUX 8/02 06:15, Duration: 20:35)
        LocalDateTime departureTime = LocalDateTime.of(2015, Month.AUGUST, 1, 16, 40);
        ZoneId departureZone = ZoneId.of("Asia/Tokyo");
        Duration flightTime = ArrivalTime.createHMDuration(20, 35);
        ZoneId arrivalZone = ZoneId.of("Europe/Luxembourg");
        LocalDateTime arrivalTime = ArrivalTime.arrivalTime(departureTime, departureZone, flightTime, arrivalZone);
        assertEquals(LocalDateTime.of(2015, Month.AUGUST, 2, 6, 15), arrivalTime);
    }

    @Test
    public void testCreateHMDuration_zero() {
        assertEquals(Duration.ZERO, ArrivalTime.createHMDuration(0, 0));
    }

    @Test
    public void testCreateHMDuration_hours() {
        assertEquals(Duration.ofHours(100), ArrivalTime.createHMDuration(100, 0));
    }

    @Test
    public void testCreateHMDuration_minutes() {
        assertEquals(Duration.ofMinutes(100), ArrivalTime.createHMDuration(0, 100));
    }
}
