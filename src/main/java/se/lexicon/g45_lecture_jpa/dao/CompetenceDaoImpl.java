package se.lexicon.g45_lecture_jpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g45_lecture_jpa.entity.Competence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class CompetenceDaoImpl implements CompetenceDao {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public Competence persist(Competence competence) {
        entityManager.persist(competence);
        return competence;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Competence> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Competence.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Competence> findAll() {
        return entityManager.createQuery("SELECT a FROM Competence a", Competence.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Competence update(Competence updated) {
        return entityManager.merge(updated);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        Competence competence = entityManager.find(Competence.class, id);
        if (competence != null) {
            entityManager.remove(competence);
        }
        // else throw exception
    }
}
