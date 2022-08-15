package core.basesyntax.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.basesyntax.dto.request.KPacRequestDto;
import core.basesyntax.dto.response.KPacResponseDto;
import core.basesyntax.mapper.KPacMapper;
import core.basesyntax.model.KPac;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kpacs")
public class KPacController {
    private final KPacService kpacService;
    private final KPacSetService kpacSetService;
    private final KPacMapper kpacMapper;

    public KPacController(KPacService kpacService, KPacSetService kpacSetService,
                          KPacMapper kpacMapper) {
        this.kpacService = kpacService;
        this.kpacSetService = kpacSetService;
        this.kpacMapper = kpacMapper;
    }

    @GetMapping
    public String getAll(Model model) {
        List<KPacResponseDto> kpacs = kpacService.getAll().stream()
                .map(kpacMapper::toDto)
                .collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(kpacs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot parse to JSON: " + kpacs, e);
        }

        model.addAttribute("kpacs", json);
        return "/kpacs/all";
    }

    @GetMapping("/add")
    public String create() {
        return "/kpacs/add";
    }

    @PostMapping
    public void create(@RequestBody @Valid KPacRequestDto requestDto) {
        kpacService.save(kpacMapper.toModel(requestDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        KPac kpac = kpacService.getById(id);
        for (KPacSet kpacSet : kpacSetService.getAllByKPac(kpac)) {
            kpacSetService.removeKPac(kpacSet, kpac);
        }
        kpacService.delete(id);
    }
}
