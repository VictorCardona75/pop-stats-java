package com.marvic.popstats.data;

import com.marvic.popstats.domain.CoreBasedStatisticalArea;

public interface CoreBasedStatisticalAreaRepository extends ReadOnlyRepository<CoreBasedStatisticalArea, String> {
    Iterable<CoreBasedStatisticalArea> findAllByTitleInOrCodeIn(Iterable<String> titles, Iterable<String> codes);
}
