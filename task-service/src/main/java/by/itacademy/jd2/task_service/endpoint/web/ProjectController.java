package by.itacademy.jd2.task_service.endpoint.web;

import by.itacademy.jd2.task_service.core.dto.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectUpdateDTO;
import by.itacademy.jd2.task_service.service.api.IProjectService;
import by.itacademy.jd2.task_service.service.util.PageConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/project")
@Validated
@RequiredArgsConstructor
public class ProjectController {
    private final IProjectService projectService;
    private final PageConverter pageConverter;
    private final ConversionService conversionService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ProjectCreateDTO projectCreateDTO){
        var project = conversionService.convert(projectService.create(projectCreateDTO), ProjectDTO.class);

        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "false") boolean archived
    ) {
        var projectPage = pageConverter.convert(
                projectService.read(page, size, archived), ProjectDTO.class
        );

        return new ResponseEntity<>(projectPage, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> get(@PathVariable UUID uuid){
        var project = conversionService.convert(projectService.read(uuid), ProjectDTO.class);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("dt_update") String updateDate,
            @RequestBody ProjectUpdateDTO projectUpdateDTO
    ) {
        var version = conversionService.convert(updateDate, LocalDateTime.class);
        var project = conversionService.convert(
                projectService.update(uuid, version, projectUpdateDTO), ProjectDTO.class
        );

        return new ResponseEntity<>(project, HttpStatus.ACCEPTED);
    }
}
