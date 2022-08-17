package core.basesyntax.dao.spring;

import core.basesyntax.component.rowmapper.KPacRowMapper;
import core.basesyntax.component.rowmapper.KPacSetRowMapper;
import core.basesyntax.dao.KPacSetDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.KPac;
import core.basesyntax.model.KPacSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class KPacSetDaoSpring implements KPacSetDao {
    private final JdbcTemplate jdbcTemplate;
    private final KPacSetRowMapper kpacSetRowMapper;
    private final KPacRowMapper kpacRowMapper;

    public KPacSetDaoSpring(JdbcTemplate jdbcTemplate, KPacSetRowMapper kpacSetRowMapper,
                            KPacRowMapper kpacRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.kpacSetRowMapper = kpacSetRowMapper;
        this.kpacRowMapper = kpacRowMapper;
    }

    @Override
    public KPacSet save(KPacSet kpacSet) {
        String sql = "INSERT INTO k_pac_sets (title) VALUES (?);";
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, kpacSet.getTitle());
                return statement;
            }, keyHolder);
            kpacSet.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
            insertKPacs(kpacSet);
            return kpacSet;
        } catch (Exception e) {
            throw new DataProcessingException("Cannot save to DB K-PAC set: " + kpacSet, e);
        }
    }

    @Override
    public Optional<KPacSet> getById(Long id) {
        String sql = "SELECT * FROM k_pac_sets WHERE id = ?";
        try {
            KPacSet kpacSet = jdbcTemplate.queryForObject(sql, new Object[] {id},
                    kpacSetRowMapper);
            if (kpacSet != null) {
                kpacSet.setKpacs(getKPacs(id));
                return Optional.of(kpacSet);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get all K-PAC sets from DB by id: " + id, e);
        }
    }

    @Override
    public List<KPacSet> getAll() {
        String sql = "SELECT s.* FROM k_pac_sets s";
        try {
            return jdbcTemplate.query(sql, kpacSetRowMapper).stream()
                    .peek(n -> n.setKpacs(getKPacs(n.getId())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get all K-PAC sets from DB", e);
        }
    }

    @Override
    public List<KPacSet> getAllByKPac(KPac kpac) {
        String sql = "SELECT s.* FROM k_pac_sets s "
                + "JOIN k_pac_sets_k_pacs ks ON s.id = ks.k_pac_set_id "
                + "WHERE ks.k_pac_id = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{kpac.getId()}, kpacSetRowMapper).stream()
                    .peek(n -> n.setKpacs(getKPacs(n.getId())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get all K-PAC sets from DB by K-PAC: "
                    + kpac, e);
        }
    }

    @Override
    public KPacSet update(KPacSet kpacSet) {
        String sql = "UPDATE k_pac_sets SET title = ? WHERE id = ?";
        try {
            jdbcTemplate.update(sql, kpacSet.getTitle(), kpacSet.getId());
            deleteKPacs(kpacSet.getId());
            insertKPacs(kpacSet);
            return kpacSet;
        } catch (Exception e) {
            throw new DataProcessingException("Cannot update K-PAC set: " + kpacSet, e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM k_pac_sets WHERE id = ?";
        try {
            deleteKPacs(id);
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            throw new DataProcessingException("Cannot delete K-PAC set from DB by id: " + id, e);
        }
    }

    private Set<KPac> getKPacs(Long setId) {
        String sql = "SELECT k.* FROM k_pac_sets_k_pacs ks JOIN k_pacs k ON k.id = ks.k_pac_id "
                + "WHERE ks.k_pac_set_id = ?";
        try {
            return new HashSet<>(jdbcTemplate.query(sql, new Object[] {setId}, kpacRowMapper));
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get all K-PACs from Set by id: " + setId, e);
        }
    }

    private void deleteKPacs(Long setId) {
        String sql = "DELETE FROM k_pac_sets_k_pacs WHERE k_pac_set_id = ?;";
        try {
            jdbcTemplate.update(sql, setId);
        } catch (Exception e) {
            throw new DataProcessingException("Cannot delete K-PACs from Set by id: " + setId, e);
        }
    }

    private void insertKPacs(KPacSet kpacSet) {
        String sql = "INSERT INTO k_pac_sets_k_pacs(k_pac_set_id, k_pac_id) VALUES(?, ?);";
        try {
            kpacSet.getKpacs()
                    .forEach(n -> jdbcTemplate.update(sql, kpacSet.getId(), n.getId()));
        } catch (Exception e) {
            throw new DataProcessingException("Cannot delete K-PACs from Set by id: "
                    + kpacSet.getId(), e);
        }
    }
}
