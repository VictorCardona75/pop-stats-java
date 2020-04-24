package com.marvic.popstats.domain;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The {@code NaturalIncrease} Class represents the difference in births and deaths over the
 * specified period of time.
 */
public final class NaturalIncrease {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final long value;
    private final long births;
    private final long deaths;

    public NaturalIncrease(@NotNull LocalDate startDate, @NotNull LocalDate endDate, long value,
                           long births,
                           long deaths) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
        this.births = births;
        this.deaths = deaths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NaturalIncrease that = (NaturalIncrease) o;
        return value == that.value &&
                births == that.births &&
                deaths == that.deaths &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, value, births, deaths);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NaturalIncrease.class.getSimpleName() + "[", "]")
                .add("startDate=" + startDate)
                .add("endDate=" + endDate)
                .add("value=" + value)
                .add("births=" + births)
                .add("deaths=" + deaths)
                .toString();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getValue() {
        return value;
    }

    public long getBirths() {
        return births;
    }

    public long getDeaths() {
        return deaths;
    }

}
