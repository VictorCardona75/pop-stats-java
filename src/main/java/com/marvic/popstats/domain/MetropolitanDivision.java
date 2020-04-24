package com.marvic.popstats.domain;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The {@code MetropolitanDivision} Class represents a county or group of counties (or equivalent
 * entities) delineated within a larger metropolitan statistical area, provided that the larger
 * metropolitan statistical area contains a single core with a population of at least 2.5 million
 * and other criteria are met. A Metropolitan Division consists of one or more main/secondary
 * counties that represent an employment center or centers, plus adjacent counties associated
 * with the main/secondary county or counties through commuting ties. Not all metropolitan
 * statistical areas will contain metropolitan divisions.
 */
public final class MetropolitanDivision extends StatisticalReportingArea {

    private final List<CountyOrEquivalent> counties;

    public MetropolitanDivision(@NotNull String code, @NotNull String title,
                                @NotNull List<PopulationEstimate> populationEstimates,
                                @NotNull List<CountyOrEquivalent> counties) {
        super(code, title, populationEstimates);
        this.counties = counties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MetropolitanDivision that = (MetropolitanDivision) o;
        return counties.equals(that.counties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), counties);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MetropolitanDivision.class.getSimpleName() + "[", "]")
                .add("code=" + getCode())
                .add("title=" + getTitle())
                .add("counties=" + counties)
                .toString();
    }

    public List<CountyOrEquivalent> getCounties() {
        return counties;
    }

}
