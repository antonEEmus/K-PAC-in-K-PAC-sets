package core.basesyntax.service.impl;

import core.basesyntax.dao.KPacDao;
import core.basesyntax.model.KPac;
import core.basesyntax.service.KPacService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class KPacServiceImpl implements KPacService {
    private final KPacDao kpacDao;

    public KPacServiceImpl(KPacDao kpacDao) {
        this.kpacDao = kpacDao;
    }

    @Override
    public KPac save(KPac kpac) {
        return kpacDao.save(kpac);
    }

    @Override
    public KPac getById(Long id) {
        return kpacDao.getById(id).orElseThrow(
                () -> new NoSuchElementException("No such K-PAC by id: " + id));
    }

    @Override
    public List<KPac> getAll() {
        return kpacDao.getAll();
    }

    @Override
    public void delete(Long id) {
        kpacDao.delete(id);
    }
}
