package by.itacademy.jd2.user_service.endpoint.web;

import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import by.itacademy.jd2.user_service.service.api.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
public class InternalUserController {
    private final IUserService userService;
    private final ConversionService conversionService;

    @PostMapping
    public ResponseEntity<UserShortDTO> get(@RequestBody String email) {
        UserShortDTO user = conversionService.convert(userService.findByEmail(email), UserShortDTO.class);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
