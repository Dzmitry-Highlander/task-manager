package by.itacademy.jd2.task_service.endpoint.web;

import by.itacademy.jd2.task_service.core.dto.create.TaskCreationDTO;
import by.itacademy.jd2.task_service.core.dto.data.TaskDTO;
import by.itacademy.jd2.task_service.core.dto.filter.FilterDTO;
import by.itacademy.jd2.task_service.core.dto.ref.ProjectRefDTO;
import by.itacademy.jd2.task_service.core.dto.ref.UserRefDTO;
import by.itacademy.jd2.task_service.core.dto.update.TaskUpdateDTO;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.service.TaskService;
import by.itacademy.jd2.task_service.service.util.PageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final ConversionService conversionService;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody TaskCreationDTO taskCreationDTO) {
        var task = conversionService.convert(taskService.create(taskCreationDTO), TaskDTO.class);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam Set<ETaskStatus> statuses,
            @RequestParam Set<ProjectRefDTO> projects,
            @RequestParam Set<UserRefDTO> implementers
    ) {
        FilterDTO filterDTO = new FilterDTO(statuses, projects, implementers);

        PageDTO<TaskDTO> pageDTO = PageConverter.convert(
                taskService.read(PageRequest.of(page, size), filterDTO), conversionService
        );

        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> get(@PathVariable(value = "uuid") UUID uuid) {
        var task = conversionService.convert(taskService.read(uuid), TaskDTO.class);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> put(
            @PathVariable(value = "uuid") UUID uuid,
            @PathVariable(value = "dt_update") String version,
            @RequestBody TaskUpdateDTO taskUpdateDTO
    ) {
        var task = conversionService.convert(taskService.update(uuid, version, taskUpdateDTO), TaskDTO.class);

        return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{uuid}/dt_update/{dt_update}/status/{status}")
    public ResponseEntity<?> patch(
            @PathVariable(value = "uuid") UUID uuid,
            @PathVariable(value = "dt_update") String version,
            @PathVariable(value = "status") ETaskStatus taskStatus
    ) {
        var task = conversionService.convert(taskService.patch(uuid, version, taskStatus), TaskDTO.class);

        return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
    }
}
