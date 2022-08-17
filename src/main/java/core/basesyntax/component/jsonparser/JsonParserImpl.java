package core.basesyntax.component.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonParserImpl implements JsonParser {
    @Override
    public String parse(Object model) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot parse to JSON: " + model, e);
        }
    }
}
