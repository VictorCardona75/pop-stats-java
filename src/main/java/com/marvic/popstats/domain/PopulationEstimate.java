package com.marvic.popstats.domain;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The {@code PopulationEstimate} Class represents an estimate for the population of a
 * {@link StatisticalReportingArea} on the specified date.  The estimate also contains references
 * to all of the components used to make the estimate.
 */
public class PopulationEstimate {

    private LocalDate date;
    private long value;
    private long numericChangeFromLast;
    private boolean fromCensus;
    private boolean base;
    private NaturalIncrease naturalIncrease;
    private NetMigration netMigration;
    private Residual residual;

    public PopulationEstimate(@NotNull LocalDate date, long value, long numericChangeFromLast,
                              boolean fromCensus, boolean base, @Nullable NaturalIncrease naturalIncrease,
                              @Nullable NetMigration netMigration, @Nullable Residual residual) {
        this.date = date;
        this.value = value;
        this.numericChangeFromLast = numericChangeFromLast;
        this.fromCensus = fromCensus;
        this.base = base;
        this.naturalIncrease = naturalIncrease;
        this.netMigration = netMigration;
        this.residual = residual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PopulationEstimate that = (PopulationEstimate) o;
        return value == that.value &&
                numericChangeFromLast == that.numericChangeFromLast &&
                fromCensus == that.fromCensus &&
                base == that.base &&
                date.equals(that.date) &&
                Objects.equals(naturalIncrease, that.naturalIncrease) &&
                Objects.equals(netMigration, that.netMigration) &&
                Objects.equals(residual, that.residual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, value, numericChangeFromLast, fromCensus, base, naturalIncrease, netMigration, residual);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PopulationEstimate.class.getSimpleName() + "[", "]")
                .add("date=" + date)
                .add("value=" + value)
                .add("numericChangeFromLast=" + numericChangeFromLast)
                .add("fromCensus=" + fromCensus)
                .add("base=" + base)
                .add("naturalIncrease=" + naturalIncrease)
                .add("netMigration=" + netMigration)
                .add("residual=" + residual)
                .toString();
    }

    public LocalDate getDate() {
        return date;
    }

    public long getValue() {
        return value;
    }

    public long getNumericChangeFromLast() {
        return numericChangeFromLast;
    }

    public boolean isFromCensus() {
        return fromCensus;
    }

    public boolean isBase() {
        return base;
    }

    public NaturalIncrease getNaturalIncrease() {
        return naturalIncrease;
    }

    public NetMigration getNetMigration() {
        return netMigration;
    }

    public Residual getResidual() {
        return residual;
    }

}
