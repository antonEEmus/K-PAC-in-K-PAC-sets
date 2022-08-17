package core.basesyntax.dao.spring;

import core.basesyntax.component.rowmapper.KPacRowMapper;
import core.basesyntax.dao.KPacDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.KPac;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class KPacDaoSpring implements KPacDao {
    private final JdbcTemplate jdbcTemplate;
    private final KPacRowMapper kpacRowMapper;

    public KPacDaoSpring(JdbcTemplate jdbcTemplate, KPacRowMapper kpacRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.kpacRowMapper = kpacRowMapper;
    }

    @Override
    public KPac save(KPac kpac) {
        try {
            String sql = "INSERT INTO k_pacs (title, description, creation_date) VALUES (?, ?, ?);";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, kpac.getTitle());
                statement.setString(2, kpac.getDescription());
                statement.setTimestamp(3, kpac.getCreationDate());
                return statement;
            }, keyHolder);
            kpac.setId((Long) keyHolder.getKey());
            return kpac;
        } catch (Exception e) {
            throw new DataProcessingException("Cannot save to DB K-PAC: " + kpac, e);
        }
    }

    @Override
    public Optional<KPac> getById(Long id) {
        try {
            String sql = "SELECT * FROM k_pacs WHERE id = ?;";
            KPac kpac = jdbcTemplate.queryForObject(sql, new Object[]{id}, kpacRowMapper);
            return Optional.ofNullable(kpac);
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get K-PAC from DB by id: " + id, e);
        }
    }

    @Override
    public List<KPac> getAll() {
        try {
            String sql = "SELECT * FROM k_pacs;";
            return jdbcTemplate.query(sql, kpacRowMapper);
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get all K-PACs from DB", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM k_pacs WHERE id = ?";
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            throw new DataProcessingException("Cannot delete K-PAC from DB by id: " + id, e);
        }
    }
}
