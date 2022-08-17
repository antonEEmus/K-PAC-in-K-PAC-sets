package core.basesyntax.component.dtomapper;

import core.basesyntax.dto.request.KPacSetRequestDto;
import core.basesyntax.dto.response.KPacSetResponseDto;
import core.basesyntax.model.KPacSet;
import core.basesyntax.service.KPacService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class KPacSetMapper implements RequestDtoMapper<KPacSetRequestDto, KPacSet>,
        ResponseDtoMapper<KPacSetResponseDto, KPacSet> {
    private final KPacService kpacService;

    public KPacSetMapper(KPacService kpacService) {
        this.kpacService = kpacService;
    }

    @Override
    public KPacSet toModel(KPacSetRequestDto dto) {
        KPacSet kpacSet = new KPacSet();
        kpacSet.setTitle(dto.getTitle());
        kpacSet.setKpacs(dto.getKpacIds().stream()
                .map(kpacService::getById)
                .collect(Collectors.toSet()));
        return kpacSet;
    }

    @Override
    public KPacSetResponseDto toDto(KPacSet kpacSet) {
        KPacSetResponseDto dto = new KPacSetResponseDto();
        dto.setId(kpacSet.getId());
        dto.setTitle(kpacSet.getTitle());
        return dto;
    }
}
