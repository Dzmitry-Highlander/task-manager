package by.itacademy.jd2.task_service.endpoint.web;

import by.itacademy.jd2.task_service.core.dto.create.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.update.ProjectUpdateDTO;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectCreateDTO projectCreateDTO) {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> read(
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "20") @Positive int size,
            @RequestParam(defaultValue = "false") boolean archived
            ) {
        return null;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> read(@PathVariable(value = "uuid") UUID uuid) {
        return null;
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(
            @PathVariable(value = "uuid") UUID uuid,
            @PathVariable(value = "dt_update") LocalDateTime version,
            @RequestBody ProjectUpdateDTO projectUpdateDTO
            ) {
        return null;
    }
}
