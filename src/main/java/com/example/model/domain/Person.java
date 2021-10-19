package com.example.model.domain;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
//@Introspected
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @NotEmpty(message = "can not be empty")
    @Size(min = 1, max = 20)
    @Getter
    @Setter
    private String name;

    @Min(18)
    @PositiveOrZero
    @Getter
    @Setter
    private int age;

    // @NotNull
    //private Gender gender;

}
