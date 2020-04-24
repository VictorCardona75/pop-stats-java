package com.marvic.popstats.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The {@code CoreBasedStatisticalArea} Class also known as a CBSA represents either metropolitan
 * statistical areas or micropolitan statistical areas. CBSAs consist of the county or counties
 * (or equivalent entities) associated with at least one core (urbanized area or urban cluster)
 * of at least 10,000 population, plus adjacent counties having a high degree of social and
 * economic integration with the core as measured through commuting ties.
 */
@Document(collection = "coreBasedStatisticalAreas")
public final class CoreBasedStatisticalArea extends StatisticalReportingArea {

    private final StatisticalAreaType areaType;
    private final List<MetropolitanDivision> divisions;
    private final List<CountyOrEquivalent> counties;

    public CoreBasedStatisticalArea(@NotNull String code, @NotNull String title,
                                    @NotNull List<PopulationEstimate> populationEstimates,
                                    @NotNull StatisticalAreaType areaType,
                                    @NotNull List<MetropolitanDivision> divisions,
                                    @NotNull List<CountyOrEquivalent> counties) {
        super(code, title, populationEstimates);
        this.areaType = areaType;
        this.divisions = divisions;
        this.counties = counties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CoreBasedStatisticalArea that = (CoreBasedStatisticalArea) o;
        return areaType == that.areaType &&
                divisions.equals(that.divisions) &&
                counties.equals(that.counties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), areaType, divisions, counties);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CoreBasedStatisticalArea.class.getSimpleName() + "[", "]")
                .add("code=" + getCode())
                .add("title=" + getTitle())
                .add("areaType=" + areaType)
                .add("divisions=" + divisions)
                .add("counties=" + counties)
                .toString();
    }

    public StatisticalAreaType getAreaType() {
        return areaType;
    }

    public List<MetropolitanDivision> getDivisions() {
        return divisions;
    }

    public List<CountyOrEquivalent> getCounties() {
        return counties;
    }

}
