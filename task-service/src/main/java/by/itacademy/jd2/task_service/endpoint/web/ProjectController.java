package by.itacademy.jd2.task_service.endpoint.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    //TODO @RequestBody ProjectCreateDTO
    @PostMapping
    public ResponseEntity<?> create() {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> read() {
        return null;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> read(@PathVariable UUID uuid) {
        return null;
    }

    //TODO @RequestBody ProjectCreateDTO or ProjectUpdateDTO
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(
            @PathVariable UUID uuid,
            @PathVariable LocalDateTime version
            ) {
        return null;
    }
}
