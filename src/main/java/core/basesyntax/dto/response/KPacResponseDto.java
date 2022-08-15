package core.basesyntax.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KPacResponseDto {
    private Long id;
    private String title;
    private String description;
    private String creationDate;
}
