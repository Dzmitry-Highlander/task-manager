package by.itacademy.jd2.comments_service.web;

import by.itacademy.jd2.comments_service.core.dto.CommentCreateDTO;
import by.itacademy.jd2.comments_service.core.dto.CommentDTO;
import by.itacademy.jd2.comments_service.dao.entity.Comment;
import by.itacademy.jd2.comments_service.service.api.ICommentService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final ICommentService commentService;
    private final ConversionService conversionService;

    public CommentController(ICommentService commentService, ConversionService conversionService) {
        this.commentService = commentService;
        this.conversionService = conversionService;
    }

    @GetMapping("/")
    public List<CommentDTO> get() {
        List<CommentDTO> dtoList = new ArrayList<>();
        for (Comment commentEntity : commentService.read()) {
            dtoList.add(conversionService.convert(commentEntity, CommentDTO.class));
        }

        return dtoList;
    }

    @GetMapping("/{uuid}")
    public CommentDTO get(@PathVariable UUID id) {
        Comment comment = commentService.read(id);

        return conversionService.convert(comment, CommentDTO.class);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody CommentCreateDTO dto) {
        this.commentService.create(dto);
    }

    @DeleteMapping("/{uuid}/dt_update/{dt_update}")
    public void delete(@PathVariable UUID id) {
        this.commentService.delete(id);
    }
}
