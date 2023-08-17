package by.itacademy.jd2.task_service.endpoint.web;

import by.itacademy.jd2.base_package.core.dto.PageDTO;
import by.itacademy.jd2.task_service.core.dto.*;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.dao.entity.Task;
import by.itacademy.jd2.task_service.service.api.ITaskService;
import by.itacademy.jd2.task_service.service.util.PageConverter;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final ITaskService taskService;
    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<PageDTO<TaskDTO>> get(
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "20") @PositiveOrZero int size,
            @RequestParam(required = false) List<ETaskStatus> status,
            @RequestParam(required = false) List<ProjectRefDTO> project,
            @RequestParam(required = false) List<UserRefDTO> implementer
    ) {
        FilterDTO filterDTO = new FilterDTO(status, project, implementer);
        Page<Task> tasks = taskService.read(page, size, filterDTO);
        PageDTO<TaskDTO> pageDTO = PageConverter.convert(tasks, TaskDTO.class, conversionService);

        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody TaskCreateMyDTO taskCreateDTO) {
        var task = conversionService.convert(taskService.create(taskCreateDTO), TaskDTO.class);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TaskDTO> findById(@PathVariable UUID uuid){
        var task = conversionService.convert(taskService.read(uuid), TaskDTO.class);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<TaskDTO> update(
            @PathVariable UUID uuid,
            @PathVariable("dt_update") String updateDate,
            @RequestBody TaskUpdateDTO taskUpdateDTO
    ) {
        var version = conversionService.convert(updateDate, LocalDateTime.class);
        var task = conversionService.convert(taskService.update(uuid, version, taskUpdateDTO), TaskDTO.class);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
