package core.basesyntax.service;

import core.basesyntax.model.KPac;
import core.basesyntax.model.KPacSet;
import java.util.List;
import java.util.Set;

public interface KPacSetService {
    KPacSet save(KPacSet kpacSet);

    KPacSet getById(Long id);

    List<KPacSet> getAll();

    List<KPacSet> getAllByKPac(KPac kpac);

    void delete(Long id);

    void removeKPac(KPacSet kpacSet, KPac kpac);

    Set<KPac> getKpacs(Long id);
}
