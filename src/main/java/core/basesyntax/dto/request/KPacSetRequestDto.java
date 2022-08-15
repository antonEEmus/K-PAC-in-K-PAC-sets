package core.basesyntax.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KPacSetRequestDto {
    @NotNull
    @Size(min = 1, max = 250)
    private String title;
    @NotNull
    @Size(min = 1)
    @JsonProperty("kpacIds")
    private List<Long> kpacIds;
}
