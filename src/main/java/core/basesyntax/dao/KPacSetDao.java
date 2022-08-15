package core.basesyntax.dao;

import core.basesyntax.model.KPac;
import core.basesyntax.model.KPacSet;
import java.util.List;
import java.util.Optional;

public interface KPacSetDao {
    KPacSet save(KPacSet kpacSet);

    Optional<KPacSet> getById(Long id);

    List<KPacSet> getAll();

    List<KPacSet> getAllByKPac(KPac kpac);

    KPacSet update(KPacSet kpacSet);

    void delete(Long id);
}
