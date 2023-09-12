package by.itacademy.jd2.user_service.endpoint.web;

import by.itacademy.jd2.user_service.core.dto.UserCreateDTO;
import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.core.dto.UserUpdateDTO;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<?> get() {
        var dtoList = new ArrayList<>();

        for (User userEntity : userService.read()) {
            dtoList.add(conversionService.convert(userEntity, UserDTO.class));
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> get(@PathVariable("uuid") UUID uuid) {
        var user = conversionService.convert(userService.read(uuid), UserDTO.class);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody UserCreateDTO dto) {
        var user = conversionService.convert(userService.create(dto), UserDTO.class);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> put(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("dt_update") String updateDate,
            @Valid @RequestBody UserUpdateDTO userUpdateDTO
    ) {
        var version = conversionService.convert(updateDate, LocalDateTime.class);
        var user = conversionService.convert(userService.update(uuid, version, userUpdateDTO), UserDTO.class);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
