package by.itacademy.jd2.task_service.endpoint.web;

import by.itacademy.jd2.task_service.core.dto.FilterDTO;
import by.itacademy.jd2.task_service.core.dto.TaskCreateMyDTO;
import by.itacademy.jd2.task_service.core.dto.TaskDTO;
import by.itacademy.jd2.task_service.core.dto.TaskUpdateDTO;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.service.api.ITaskService;
import by.itacademy.jd2.task_service.service.util.PageConverter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@Validated
@RequiredArgsConstructor
public class TaskController {
    private final ITaskService taskService;
    private final ConversionService conversionService;
    private final PageConverter pageConverter;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid TaskCreateMyDTO taskCreateDTO) {
        var task = conversionService.convert(taskService.create(taskCreateDTO), TaskDTO.class);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> get(
            @RequestParam(required = false, defaultValue = "0") @PositiveOrZero Integer page,
            @RequestParam(required = false, defaultValue = "20") @PositiveOrZero Integer size,
            @RequestParam(required = false) List<UUID> project,
            @RequestParam(required = false) List<UUID> implementer,
            @RequestParam(required = false) List<ETaskStatus> status
    ) {
        FilterDTO filterDTO = FilterDTO.builder()
                .project(project)
                .implementer(implementer)
                .status(status)
                .build();
        var taskPage =  pageConverter.convert(taskService.read(page, size, filterDTO), TaskDTO.class);

        return new ResponseEntity<>(taskPage, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> get(@PathVariable UUID uuid) {
        var task = conversionService.convert(taskService.read(uuid), TaskDTO.class);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("dt_update") String updateDate,
            @RequestBody @Valid TaskUpdateDTO taskUpdateDTO
            ) {
        var version = conversionService.convert(updateDate, LocalDateTime.class);
        var task = conversionService.convert(
          taskService.update(uuid, version, taskUpdateDTO), TaskDTO.class
        );

        return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{uuid}/dt_update/{dt_update}/status/{status}")
    public ResponseEntity<?> statusPatch(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("dt_update") String updateDate,
            @PathVariable ETaskStatus status
    ) {
        var version = conversionService.convert(updateDate, LocalDateTime.class);
        var task = conversionService.convert(taskService.updateStatus(uuid, version, status), TaskDTO.class);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
