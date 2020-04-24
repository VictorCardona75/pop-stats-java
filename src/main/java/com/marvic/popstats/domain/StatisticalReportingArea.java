package com.marvic.popstats.domain;

import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public abstract class StatisticalReportingArea {

    @MongoId
    private final String code;
    private final String title;
    private final List<PopulationEstimate> populationEstimates;

    public StatisticalReportingArea(@NotNull String code, @NotNull String title,
                                    @NotNull List<PopulationEstimate> populationEstimates) {
        this.code = code;
        this.title = title;
        this.populationEstimates = populationEstimates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticalReportingArea that = (StatisticalReportingArea) o;
        return code.equals(that.code) &&
                title.equals(that.title) &&
                populationEstimates.equals(that.populationEstimates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, title, populationEstimates);
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<PopulationEstimate> getPopulationEstimates() {
        return populationEstimates;
    }

}
