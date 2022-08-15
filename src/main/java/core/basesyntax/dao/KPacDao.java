package core.basesyntax.dao;

import core.basesyntax.model.KPac;
import java.util.List;
import java.util.Optional;

public interface KPacDao {
    KPac save(KPac kpac);

    Optional<KPac> getById(Long id);

    List<KPac> getAll();

    void delete(Long id);
}
