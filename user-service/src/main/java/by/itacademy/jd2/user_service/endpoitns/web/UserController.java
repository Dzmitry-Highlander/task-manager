package by.itacademy.jd2.user_service.endpoitns.web;

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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    //TODO привести в порядок все return
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
    public ResponseEntity<?> post(@RequestBody UserCreateDTO dto) {
        User userEntity = userService.create(dto);
        var user = conversionService.convert(userEntity, UserDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(
            @PathVariable UUID uuid,
            //TODO Formatter в чате, относится к настройкам Long -> LocalDateTime
            @PathVariable("dt_update") Long updateDate,
            @RequestBody UserCreateDTO userCreateDTO) {
        //TODO Converter
        User updatedUser = userService.update(uuid, updateDate, userCreateDTO);
        var user = this.conversionService.convert(updatedUser, UserDTO.class);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
