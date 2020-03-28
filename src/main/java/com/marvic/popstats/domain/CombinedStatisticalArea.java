package com.marvic.popstats.domain;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The {@code CombinedStatisticalArea} Class represents two or more adjacent core based
 * statistical areas (CBSAs) that have an employment interchange measure of 15 or more.  The
 * employment interchange measure (EIM) is a measure of ties between two adjacent entities. It is
 * the sum of the percentage of workers living in the smaller entity who work in the larger
 * entity and the percentage of employment in the smaller entity that is accounted for by workers
 * who reside in the larger entity.
 */
public class CombinedStatisticalArea extends StatisticalReportingArea {

    private List<CoreBasedStatisticalArea> coreBasedStatisticalAreas;

    public CombinedStatisticalArea(@NotNull String code, @NotNull String title,
                                   @NotNull List<PopulationEstimate> populationEstimates,
                                   @NotNull List<CoreBasedStatisticalArea> coreBasedStatisticalAreas) {
        super(code, title, populationEstimates);
        this.coreBasedStatisticalAreas = coreBasedStatisticalAreas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CombinedStatisticalArea that = (CombinedStatisticalArea) o;
        return coreBasedStatisticalAreas.equals(that.coreBasedStatisticalAreas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coreBasedStatisticalAreas);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CombinedStatisticalArea.class.getSimpleName() + "[", "]")
                .add("code=" + getCode())
                .add("title=" + getTitle())
                .add("coreBasedStatisticalAreas=" + coreBasedStatisticalAreas)
                .toString();
    }

    public List<CoreBasedStatisticalArea> getCoreBasedStatisticalAreas() {
        return coreBasedStatisticalAreas;
    }

}
