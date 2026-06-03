package com.example.person_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.person_api.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
}