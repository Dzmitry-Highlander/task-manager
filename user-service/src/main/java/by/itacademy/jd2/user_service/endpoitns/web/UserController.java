package by.itacademy.jd2.user_service.endpoitns.web;

import by.itacademy.jd2.user_service.core.dto.UserCreateDTO;
import by.itacademy.jd2.user_service.core.dto.UserDTO;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.service.api.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final IUserService userService;
    private final ConversionService conversionService;

    @GetMapping("/")
    public List<UserDTO> get() {
        List<UserDTO> dtoList = new ArrayList<>();

        for (User userEntity : userService.read()) {
            dtoList.add(conversionService.convert(userEntity, UserDTO.class));
        }

        return dtoList;
    }

    @GetMapping("/{uuid}")
    public UserDTO get(@PathVariable UUID id) {
        User user = userService.read(id);

        return conversionService.convert(user, UserDTO.class);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> post(@RequestBody UserCreateDTO dto) {
        User userEntity = userService.create(dto);
        UserDTO userDTO = conversionService.convert(userEntity, UserDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    //TODO update()
}
