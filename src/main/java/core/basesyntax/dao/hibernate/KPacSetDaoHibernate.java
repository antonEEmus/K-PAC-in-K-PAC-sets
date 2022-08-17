package core.basesyntax.dao.hibernate;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.KPacSetDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.KPac;
import core.basesyntax.model.KPacSet;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class KPacSetDaoHibernate extends AbstractDao<KPacSet> implements KPacSetDao {
    public KPacSetDaoHibernate(SessionFactory sessionFactory) {
        super(sessionFactory, KPacSet.class);
    }

    @Override
    public Optional<KPacSet> getById(Long id) {
        String hql = "FROM KPacSet sets LEFT JOIN FETCH sets.kpacs "
                + "WHERE sets.id = :id";
        try (Session session = sessionFactory.openSession()) {
            Query<KPacSet> query = session.createQuery(hql, KPacSet.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get K-PAC set from DB by id: " + id, e);
        }
    }

    @Override
    public List<KPacSet> getAllByKPac(KPac kpac) {
        String hql = "FROM KPacSet kpset JOIN FETCH kpset.kpacs "
                + "WHERE :kpac IN ELEMENTS(kpset.kpacs)";
        try (Session session = sessionFactory.openSession()) {
            Query<KPacSet> query = session.createQuery(hql, KPacSet.class);
            query.setParameter("kpac", kpac);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get K-PAC set from DB by kpac: " + kpac, e);
        }
    }
}
