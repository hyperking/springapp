package com.springapi.repositories.mongo;

import com.springapi.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel="person", path="person")
public interface PersonRepo extends CrudRepository<Person, Integer> {
//    List<Person> findByPid(@Param("pid") String pid);
}
