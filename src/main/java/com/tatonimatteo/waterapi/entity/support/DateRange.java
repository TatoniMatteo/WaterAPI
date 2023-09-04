package com.tatonimatteo.waterapi.entity.support;

import java.sql.Date;
import java.sql.Time;

/**
 * Represents a date and time range with start and end dates and times.
 *
 * @param startDate -- GETTER --
 *                  Gets the start date of the date range.
 * @param endDate   -- GETTER --
 *                  Gets the end date of the date range.
 * @param startTime -- GETTER --
 *                  Gets the start time of the date range.
 * @param endTime   -- GETTER --
 *                  Gets the end time of the date range.
 */
public record DateRange(Date startDate, Date endDate, Time startTime, Time endTime) {

    /**
     * Constructs a DateRange object with the specified start and end dates and times.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param startTime the start time
     * @param endTime   the end time
     */
    public DateRange {
    }

}

