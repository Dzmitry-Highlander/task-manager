package by.itacademy.jd2.task_service.endpoint.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    //TODO @RequestBody TaskCreateDTO
    @PostMapping
    public ResponseEntity<?> create() {
        return null;
    }

    //TODO нужен ли required = false? status = Enum, project and implementer = ???
    @GetMapping
    public ResponseEntity<?> read(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam Object project,
            @RequestParam Object implementer,
            @RequestParam Object status
    ) {
        return null;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> read(@PathVariable(value = "uuid") UUID uuid) {
        return null;
    }

    //TODO @RequestBody TaskCreateDTO или TaskUpdateDTO
    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(
            @PathVariable(value = "uuid") UUID uuid,
            @PathVariable(value = "dt_update") LocalDateTime version
            ) {
        return null;
    }

    //TODO status = Enum
    @PatchMapping("/{uuid}/dt_update/{dt_update}/status/{status}")
    public ResponseEntity<?> update(
            @PathVariable(value = "uuid") UUID uuid,
            @PathVariable(value = "dt_update") LocalDateTime version,
            @PathVariable(value = "status") Object status
    ) {
        return null;
    }
}
