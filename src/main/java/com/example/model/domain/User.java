package com.example.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class User {

    public static final String DEFAULT_ROLE = "VIEW";

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

    @Builder.Default
    private String role = DEFAULT_ROLE;

    private String token;
}
