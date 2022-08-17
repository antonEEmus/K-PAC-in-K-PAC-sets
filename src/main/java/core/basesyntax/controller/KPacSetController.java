package core.basesyntax.controller;

import core.basesyntax.component.dtomapper.KPacMapper;
import core.basesyntax.component.dtomapper.KPacSetMapper;
import core.basesyntax.component.jsonparser.JsonParser;
import core.basesyntax.dto.request.KPacSetRequestDto;
import core.basesyntax.dto.response.KPacResponseDto;
import core.basesyntax.dto.response.KPacSetResponseDto;
import core.basesyntax.model.KPacSet;
import core.basesyntax.service.KPacService;
import core.basesyntax.service.KPacSetService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class KPacSetController {
    private final KPacSetService kpacSetService;
    private final KPacSetMapper kpacSetMapper;
    private final KPacService kpacService;
    private final KPacMapper kpacMapper;
    private final JsonParser jsonParser;

    public KPacSetController(KPacSetService kpacSetService, KPacSetMapper kpacSetMapper,
                             KPacService kpacService, KPacMapper kpacMapper,
                             JsonParser jsonParser) {
        this.kpacSetService = kpacSetService;
        this.kpacSetMapper = kpacSetMapper;
        this.kpacService = kpacService;
        this.kpacMapper = kpacMapper;
        this.jsonParser = jsonParser;
    }

    @GetMapping("/sets")
    public String getAll(Model model) {
        List<KPacSetResponseDto> sets = kpacSetService.getAll().stream()
                .map(kpacSetMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("sets", jsonParser.parse(sets));
        return "/sets/all";
    }

    @GetMapping("/sets/add")
    public String create(Model model) {
        List<KPacResponseDto> kpacs = kpacService.getAll().stream()
                .map(kpacMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("kpacs", kpacs);
        return "/sets/add";
    }

    @PostMapping("/sets")
    public void create(@RequestBody @Valid KPacSetRequestDto requestDto) {
        kpacSetService.save(kpacSetMapper.toModel(requestDto));
    }

    @GetMapping("/set/{id}")
    public String getKpacs(@PathVariable Long id, Model model) {
        List<KPacResponseDto> kpacs = kpacSetService.getKpacs(id).stream()
                .map(kpacMapper::toDto)
                .collect(Collectors.toList());
        KPacSet kpacSet = kpacSetService.getById(id);
        model.addAttribute("kpacs", jsonParser.parse(kpacs));
        model.addAttribute("title", kpacSet.getTitle());
        return "/kpacs/by-set";
    }

    @DeleteMapping("/sets/{id}")
    public void delete(@PathVariable Long id) {
        kpacSetService.delete(id);
    }
}
