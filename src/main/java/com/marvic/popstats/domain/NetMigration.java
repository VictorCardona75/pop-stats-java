package com.marvic.popstats.domain;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The {@code NetMigration} Class represents the populational change that is attributed to
 * migration over the specified time range.  Net migration includes both international and
 * domestic components.
 */
public class NetMigration {

    private LocalDate startDate;
    private LocalDate endDate;
    private long value;
    private long netInternationalMigration;
    private long netDomesticMigration;

    public NetMigration(@NotNull LocalDate startDate, @NotNull LocalDate endDate, long value,
                        long netInternationalMigration, long netDomesticMigration) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
        this.netInternationalMigration = netInternationalMigration;
        this.netDomesticMigration = netDomesticMigration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetMigration that = (NetMigration) o;
        return value == that.value &&
                netInternationalMigration == that.netInternationalMigration &&
                netDomesticMigration == that.netDomesticMigration &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, value, netInternationalMigration, netDomesticMigration);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NetMigration.class.getSimpleName() + "[", "]")
                .add("startDate=" + startDate)
                .add("endDate=" + endDate)
                .add("value=" + value)
                .add("netInternationalMigration=" + netInternationalMigration)
                .add("netDomesticMigration=" + netDomesticMigration)
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

    public long getNetInternationalMigration() {
        return netInternationalMigration;
    }

    public long getNetDomesticMigration() {
        return netDomesticMigration;
    }

}
