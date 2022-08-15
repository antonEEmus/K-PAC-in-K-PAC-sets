package core.basesyntax.service;

import core.basesyntax.model.KPac;
import java.util.List;

public interface KPacService {
    KPac save(KPac kpac);

    KPac getById(Long id);

    List<KPac> getAll();

    void delete(Long id);
}
