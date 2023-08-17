package by.itacademy.jd2.task_service.endpoint.web;

import by.itacademy.jd2.base_package.core.dto.PageDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectCreateDTO;
import by.itacademy.jd2.task_service.core.dto.ProjectDTO;
import by.itacademy.jd2.task_service.service.api.IProjectService;
import by.itacademy.jd2.task_service.service.util.PageConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@Validated
@RequiredArgsConstructor
public class ProjectController {
    private final IProjectService projectService;
    private final PageConverter pageConverter;
    private final ConversionService conversionService;

    @PostMapping
    public ResponseEntity<ProjectDTO> save(@RequestBody @Valid ProjectCreateDTO projectCreateDTO){
        ProjectDTO dto = conversionService.convert(projectService.create(projectCreateDTO), ProjectDTO.class);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageDTO<ProjectDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "false") boolean archived
    ) {
        PageDTO<ProjectDTO> projectPageDTO = pageConverter.convert(
                projectService.read(page, size, archived), ProjectDTO.class
        );

        return new ResponseEntity<>(projectPageDTO, HttpStatus.OK);
    }
}
