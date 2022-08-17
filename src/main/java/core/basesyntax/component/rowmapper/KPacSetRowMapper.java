package core.basesyntax.component.rowmapper;

import core.basesyntax.model.KPacSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class KPacSetRowMapper implements RowMapper<KPacSet> {
    @Override
    public KPacSet mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        KPacSet kpacSet = new KPacSet();
        kpacSet.setId(resultSet.getLong("id"));
        kpacSet.setTitle(resultSet.getString("title"));
        return kpacSet;
    }
}
