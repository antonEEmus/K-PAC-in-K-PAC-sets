package core.basesyntax.mapper;

import core.basesyntax.dto.request.KPacRequestDto;
import core.basesyntax.dto.response.KPacResponseDto;
import core.basesyntax.model.KPac;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class KPacMapper implements RequestDtoMapper<KPacRequestDto, KPac>,
        ResponseDtoMapper<KPacResponseDto, KPac> {
    @Override
    public KPac toModel(KPacRequestDto dto) {
        KPac kpac = new KPac();
        kpac.setTitle(dto.getTitle());
        kpac.setDescription(dto.getDescription());
        kpac.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        return kpac;
    }

    @Override
    public KPacResponseDto toDto(KPac kpac) {
        KPacResponseDto dto = new KPacResponseDto();
        dto.setId(kpac.getId());
        dto.setTitle(kpac.getTitle());
        dto.setDescription(kpac.getDescription());
        dto.setCreationDate(kpac.getCreationDate().toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return dto;
    }
}
