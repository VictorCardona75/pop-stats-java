package com.marvic.popstats.domain;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The {@code CountyOrEquivalent} Class represents the smallest component of core based
 * statistical areas for which population estimates are made.  Counties are classified as either
 * central or outlying.  Central counties comprise all or most of the core urban area.  Outlying
 * counties are counties where one-quarter or more of employed residents work in central
 * counties, or where one-quarter or more of employment is composed of workers who live in
 * central counties.
 */
public final class CountyOrEquivalent extends StatisticalReportingArea {

    private final CountyType countyType;

    public CountyOrEquivalent(@NotNull String code, @NotNull String title,
                              @NotNull List<PopulationEstimate> populationEstimates,
                              @NotNull CountyType countyType) {
        super(code, title, populationEstimates);
        this.countyType = countyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CountyOrEquivalent that = (CountyOrEquivalent) o;
        return countyType == that.countyType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countyType);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CountyOrEquivalent.class.getSimpleName() + "[", "]")
                .add("code=" + getCode())
                .add("title=" + getTitle())
                .add("countyType=" + countyType)
                .toString();
    }

    public CountyType getCountyType() {
        return countyType;
    }

}
