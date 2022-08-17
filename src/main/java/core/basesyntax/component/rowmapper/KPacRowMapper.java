package core.basesyntax.component.rowmapper;

import core.basesyntax.model.KPac;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class KPacRowMapper implements RowMapper<KPac> {
    @Override
    public KPac mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        KPac kpac = new KPac();
        kpac.setId(resultSet.getLong("id"));
        kpac.setTitle(resultSet.getString("title"));
        kpac.setDescription(resultSet.getString("description"));
        kpac.setCreationDate(resultSet.getTimestamp("creation_date"));
        return kpac;
    }
}
