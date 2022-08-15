package core.basesyntax.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KPacRequestDto {
    @NotNull
    @Size(min = 1, max = 250)
    private String title;
    @NotNull
    @Size(min = 1, max = 2000)
    private String description;
}
