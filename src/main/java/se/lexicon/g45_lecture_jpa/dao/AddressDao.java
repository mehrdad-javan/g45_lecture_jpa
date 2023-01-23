package se.lexicon.g45_lecture_jpa.dao;

import se.lexicon.g45_lecture_jpa.entity.Address;

import java.util.Collection;
import java.util.Optional;

public interface AddressDao {

    Address persist(Address address);

    Optional<Address> findById(Integer id);

    Collection<Address> findAll();

    Address update(Address address);

    void remove(Integer id);
}
