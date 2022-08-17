package core.basesyntax.dao.hibernate;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.KPacDao;
import core.basesyntax.model.KPac;
import org.hibernate.SessionFactory;

public class KPacDaoHibernate extends AbstractDao<KPac> implements KPacDao {
    public KPacDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory, KPac.class);
    }
}
