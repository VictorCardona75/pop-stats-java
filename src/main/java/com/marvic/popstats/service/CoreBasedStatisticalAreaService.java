package com.marvic.popstats.service;

import com.google.common.base.Preconditions;
import com.marvic.popstats.data.CoreBasedStatisticalAreaRepository;
import com.marvic.popstats.domain.CoreBasedStatisticalArea;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoreBasedStatisticalAreaService {

    private final CoreBasedStatisticalAreaRepository repository;

    public CoreBasedStatisticalAreaService(CoreBasedStatisticalAreaRepository repository) {
        this.repository = repository;
    }

    public Optional<CoreBasedStatisticalArea> findByCode(String code) {
        return repository.findById(code);
    }

    public List<CoreBasedStatisticalArea> findAll() {
        return convertToList(repository.findAll());
    }

    public List<CoreBasedStatisticalArea> findAllBy(List<String> codes, List<String> titles) {
        Preconditions.checkNotNull(codes);
        Preconditions.checkNotNull(titles);

        return convertToList(repository.findAllByTitleInOrCodeIn(titles, codes));
    }

    private List<CoreBasedStatisticalArea> convertToList(Iterable<CoreBasedStatisticalArea> iterable) {
        List<CoreBasedStatisticalArea> list = new ArrayList<>();
        for (CoreBasedStatisticalArea c : iterable) {
            list.add(c);
        }

        return list;
    }
}
