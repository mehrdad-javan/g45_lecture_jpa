package se.lexicon.g45_lecture_jpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g45_lecture_jpa.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class AddressDaoImpl implements AddressDao {


    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional
    public Address persist(Address address) {
        entityManager.persist(address);
        return address;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Address> findById(Integer id) {
        //return Optional.ofNullable(entityManager.find(Address.class, id));

       /*return entityManager.createQuery("select a from Address a where a.id = ?1")
                .setParameter(1, id)
                .getResultStream()
                .findFirst();
        */

        return entityManager.createNamedQuery("Address.findById", Address.class)
                .setParameter(1,id)
                .getResultStream()
                .findFirst();

    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Address> findAll() {
        return entityManager.createQuery("select a from Address  a", Address.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Address update(Address address) {
        return entityManager.merge(address);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        Address result = entityManager.find(Address.class, id);
        if (result != null){
            entityManager.remove(result);
        } else {
            //todo: add -> throws a custom exception
        }

    }
}
