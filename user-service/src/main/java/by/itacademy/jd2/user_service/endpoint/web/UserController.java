package by.itacademy.jd2.user_service.endpoint.web;

import by.itacademy.jd2.user_service.core.dto.UserCreateDTO;
import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final ConversionService conversionService;

    @GetMapping("/")
    public ResponseEntity<?> get() {
        var dtoList = new ArrayList<>();

        for (User userEntity : userService.read()) {
            dtoList.add(conversionService.convert(userEntity, UserDTO.class));
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> get(@PathVariable UUID uuid) {
        var user = conversionService.convert(userService.read(uuid), UserDTO.class);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> post(@RequestBody UserCreateDTO dto) {
        var user = conversionService.convert(userService.create(dto), UserDTO.class);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("dt_update") String updateDate,
            @RequestBody UserCreateDTO userCreateDTO) {
        var user = conversionService.convert(userService.update(uuid, updateDate, userCreateDTO), UserDTO.class);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
