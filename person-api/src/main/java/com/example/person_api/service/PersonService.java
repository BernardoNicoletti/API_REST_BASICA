package com.example.person_api.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.person_api.dto.PersonPatchRequest;
import com.example.person_api.dto.PersonRequest;
import com.example.person_api.dto.PersonResponse;
import com.example.person_api.model.Person;
import com.example.person_api.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<PersonResponse> findAll() {
        return repository.findAll()
            .stream()
            .map(p -> toResponse(p))
            .toList();
    }

    public PersonResponse findById(Long id) {
        Person person = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada: " + id));
        return toResponse(person);
    }

    public PersonResponse create(PersonRequest req) {
        Person person = new Person();
        person.setNome(req.getNome());
        person.setSobrenome(req.getSobrenome());
        person.setIdade(req.getIdade());
        person.setGenero(req.getGenero());
        return toResponse(repository.save(person));
    }

    public PersonResponse update(Long id, PersonRequest req) {
        Person person = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada: " + id));
        person.setNome(req.getNome());
        person.setSobrenome(req.getSobrenome());
        person.setIdade(req.getIdade());
        person.setGenero(req.getGenero());
        return toResponse(repository.save(person));
    }

    public PersonResponse patch(Long id, PersonPatchRequest req) {
        Person person = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada: " + id));
        // Só altera o que veio no body (diferente do PUT!)
        if (req.getNome() != null)      person.setNome(req.getNome());
        if (req.getSobrenome() != null) person.setSobrenome(req.getSobrenome());
        if (req.getIdade() != null)     person.setIdade(req.getIdade());
        if (req.getGenero() != null)    person.setGenero(req.getGenero());
        return toResponse(repository.save(person));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Pessoa não encontrada: " + id);
        }
        repository.deleteById(id);
    }

    // Converte entidade → DTO de resposta
    private PersonResponse toResponse(Person p) {
        PersonResponse res = new PersonResponse();
        res.setId(p.getId());
        res.setNome(p.getNome());
        res.setSobrenome(p.getSobrenome());
        res.setIdade(p.getIdade());
        res.setGenero(p.getGenero());
        return res;
    }
}