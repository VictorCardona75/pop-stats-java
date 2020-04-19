package com.marvic.popstats.api;

import com.marvic.popstats.domain.CoreBasedStatisticalArea;
import com.marvic.popstats.service.CoreBasedStatisticalAreaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@RestController
@RequestMapping(path = "/population/cbsas")
public class CoreBasedStatisticalAreaController {

    private final CoreBasedStatisticalAreaService service;

    public CoreBasedStatisticalAreaController(CoreBasedStatisticalAreaService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CoreBasedStatisticalArea> getAllUnlessFiltered(@RequestParam(name = "code") Optional<List<String>> codes,
                                                    @RequestParam(name = "title") Optional<List<String>> titles) {
        List<CoreBasedStatisticalArea> result;

        if (codes.isEmpty() && titles.isEmpty()) {
            result = service.findAll();
        } else {
            List<String> codeList = codes.orElse(List.of());
            List<String> titleList = titles.orElse(List.of());

            // Titles will contain commas which split the title in two.  So scan titles for splits and recombine.
            ListIterator<String> iter = titleList.listIterator();
            while (iter.hasNext()) {
                String title = iter.next();
                if (title.matches("(?:[A-Z]{2}-?)+") && iter.hasPrevious()) {
                    iter.remove();
                    String previousTitle = iter.previous();
                    iter.set(previousTitle + ", " + title);
                }
            }

            result = service.findAllBy(codeList, titleList);
        }

        if (result.isEmpty()) {
            throw new ContentNotFoundException("No results found");
        }

        return new Result<>(result);
    }

    @GetMapping(path = "/{code:\\d{5}}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CoreBasedStatisticalArea> getByCode(@PathVariable String code) {
        Optional<CoreBasedStatisticalArea> cbsa = service.findByCode(code);

        if (cbsa.isEmpty()) {
            throw new ContentNotFoundException("No results found");
        }

        return new Result<>(List.of(cbsa.get()));
    }
}
