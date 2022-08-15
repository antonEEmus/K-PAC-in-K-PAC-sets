package core.basesyntax.service.impl;

import core.basesyntax.dao.KPacSetDao;
import core.basesyntax.model.KPac;
import core.basesyntax.model.KPacSet;
import core.basesyntax.service.KPacSetService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class KPacSetServiceImpl implements KPacSetService {
    private final KPacSetDao kpacSetDao;

    public KPacSetServiceImpl(KPacSetDao kpacSetDao) {
        this.kpacSetDao = kpacSetDao;
    }

    @Override
    public KPacSet save(KPacSet kpacSet) {
        return kpacSetDao.save(kpacSet);
    }

    @Override
    public KPacSet getById(Long id) {
        return kpacSetDao.getById(id).orElseThrow(
                () -> new NoSuchElementException("No such K-PAC set by id: " + id));
    }

    @Override
    public List<KPacSet> getAll() {
        return kpacSetDao.getAll();
    }

    @Override
    public List<KPacSet> getAllByKPac(KPac kpac) {
        return kpacSetDao.getAllByKPac(kpac);
    }

    @Override
    public void delete(Long id) {
        kpacSetDao.delete(id);
    }

    @Override
    public void removeKPac(KPacSet kpacSet, KPac kpac) {
        kpacSet.getKpacs().remove(kpac);
        kpacSetDao.update(kpacSet);
    }

    @Override
    public Set<KPac> getKpacs(Long id) {
        return getById(id).getKpacs();
    }
}
