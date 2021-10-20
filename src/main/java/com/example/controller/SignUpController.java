package com.example.controller;

import com.example.model.dtos.UserDto;
import com.example.model.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Controller("/signup")
@Secured(SecurityRule.IS_ANONYMOUS)
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @Post
    public Single<HttpResponse<UserDto>> registerUser(UserDto userDto) {

        Optional<UserDto> existingUser = userService.findUser(userDto.getUsername());

        return Single.just(existingUser.map(HttpResponse::badRequest)
                .orElse(HttpResponse.ok(userService.createUser(userDto))));
    }
}
