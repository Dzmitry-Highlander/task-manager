package by.itacademy.jd2.task_service.endpoint.web;

import by.itacademy.jd2.base_package.core.dto.PageDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectUpdateDTO;
import by.itacademy.jd2.task_service.service.api.IProjectService;
import by.itacademy.jd2.task_service.service.util.PageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final IProjectService projectService;
    private final ConversionService conversionService;
    private final PageConverter pageConverter;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectCreateDTO projectCreateDTO) {
        ProjectDTO dto = conversionService.convert(projectService.create(projectCreateDTO), ProjectDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> read(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "false") boolean archived
    ) {
        PageDTO<ProjectDTO> projectPageDTO = pageConverter.convertPageToDTO(
                projectService.read(page, size, archived), ProjectDTO.class
        );

        return new ResponseEntity<>(projectPageDTO, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> read(@PathVariable("uuid") UUID uuid) {
        ProjectDTO dto = conversionService.convert(projectService.read(uuid), ProjectDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("dt_update") String updateDate,
            @RequestBody ProjectUpdateDTO projectUpdateDTO
    ) {
        var version = conversionService.convert(updateDate, LocalDateTime.class);
        var project = conversionService.convert(projectService.update(uuid, version, projectUpdateDTO), ProjectDTO.class);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }
}
