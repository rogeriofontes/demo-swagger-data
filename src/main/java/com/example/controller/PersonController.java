package com.example.controller;

import com.example.controller.http.Message;
import com.example.model.domain.Person;
import com.example.model.repository.PersonRepository;
import com.example.model.service.PersonService;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Inject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
//@Controller("/persons")
@Controller("${person.controller.path:/persons1}")
//@Operation(summary = "Creates a new bar object adding a decorated id and the current time",description = "Showcase of the creation of a dto")
public class PersonController {

    /*

@ApiResponse(responseCode = "201", description = "Bar object correctly created",content = @Content(mediaType = "application/json",schema = @Schema(type="BarDto")))
@ApiResponse(responseCode = "400", description = "Invalid id Supplied")
@ApiResponse(responseCode = "500", description = "Remote error, server is going nuts")    @Tag(name = "create")
     */

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Inject
    private PersonService personService;

    @Inject
    private PersonRepository personRepository;

    @Post()
    public HttpResponse<?> savePerson(@Body @Valid Person person) {
        this.personRepository.save(person);
        return HttpResponse.status(HttpStatus.CREATED).body(new Message(HttpStatus.CREATED.getCode(), "Saved successfully !"));
    }

    @Get("/hi")
    @Operation(summary = "Get users",
            description = "Get list of users")
    public HttpResponse<?> getHi() {
        LOG.info("Sau HI");
        return HttpResponse.status(HttpStatus.OK).body(this.personService.sayHi());
    }

    @Version("1")
    @Get("{?max,offset}")
    public List<Person> findAll(@Nullable Integer max, @Nullable Integer offset) {
        return getPeople().stream()
                .skip(offset == null ? 0 : offset)
                .limit(max == null ? 10000 : max)
                .collect(Collectors.toList());
    }

    @Version("2")
    @Get("?max,offset")
    public List<Person> findAllV2(@NotNull Integer max, @NotNull Integer offset) {
        return getPeople().stream()
                .skip(offset == null ? 0 : offset)
                .limit(max == null ? 10000 : max)
                .collect(Collectors.toList());
    }

    private List<Person> getPeople() {
        Iterable<Person> all = personRepository.findAll();
        List<Person> persons = new ArrayList<>();
        all.iterator().forEachRemaining(persons::add);
        return persons;
    }

    /*@Get()
    public HttpResponse<?> getPersons() {
        return HttpResponse.status(HttpStatus.OK).body(this.personRepository.findAll());
    }*/


    @Get("/{id}")
    public Optional<Person> findById(Long id) {
        Optional<Person> personById = personRepository.findById(id);
        return personById.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }


}



