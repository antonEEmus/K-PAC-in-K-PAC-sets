package core.basesyntax.dao.impl;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.KPacDao;
import core.basesyntax.model.KPac;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class KPacDaoImpl extends AbstractDao<KPac> implements KPacDao {
    public KPacDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, KPac.class);
    }
}
