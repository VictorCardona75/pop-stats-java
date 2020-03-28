package com.marvic.popstats.domain;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The {@code Residual} Class represents the change in population over the specified time range
 * which cannot be attributed to either natural increase or net migration.
 */
public class Residual {

    private LocalDate startDate;
    private LocalDate endDate;
    private long value;

    public Residual(@NotNull LocalDate startDate, @NotNull LocalDate endDate, long value) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Residual residual = (Residual) o;
        return value == residual.value &&
                startDate.equals(residual.startDate) &&
                endDate.equals(residual.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Residual.class.getSimpleName() + "[", "]")
                .add("startDate=" + startDate)
                .add("endDate=" + endDate)
                .add("value=" + value)
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

}
