package core.basesyntax.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.basesyntax.dto.request.KPacSetRequestDto;
import core.basesyntax.dto.response.KPacResponseDto;
import core.basesyntax.dto.response.KPacSetResponseDto;
import core.basesyntax.mapper.KPacMapper;
import core.basesyntax.mapper.KPacSetMapper;
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

    public KPacSetController(KPacSetService kpacSetService, KPacSetMapper kpacSetMapper,
                             KPacService kpacService, KPacMapper kpacMapper) {
        this.kpacSetService = kpacSetService;
        this.kpacSetMapper = kpacSetMapper;
        this.kpacService = kpacService;
        this.kpacMapper = kpacMapper;
    }

    @GetMapping("/sets")
    public String getAll(Model model) {
        List<KPacSetResponseDto> sets = kpacSetService.getAll().stream()
                .map(kpacSetMapper::toDto)
                .collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(sets);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot parse to JSON: " + sets, e);
        }

        model.addAttribute("sets", json);
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
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(kpacs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot parse to JSON: " + kpacs, e);
        }
        KPacSet kpacSet = kpacSetService.getById(id);
        model.addAttribute("kpacs", json);
        model.addAttribute("title", kpacSet.getTitle());
        return "/kpacs/by-set";
    }

    @DeleteMapping("/sets/{id}")
    public void delete(@PathVariable Long id) {
        kpacSetService.delete(id);
    }
}
