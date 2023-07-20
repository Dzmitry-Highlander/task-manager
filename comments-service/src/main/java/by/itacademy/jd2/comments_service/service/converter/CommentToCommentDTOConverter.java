package by.itacademy.jd2.comments_service.service.converter;

import by.itacademy.jd2.comments_service.core.dto.CommentDTO;
import by.itacademy.jd2.comments_service.core.dto.TaskDTO;
import by.itacademy.jd2.comments_service.core.dto.UserDTO;
import by.itacademy.jd2.comments_service.dao.entity.Comment;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;

public class CommentToCommentDTOConverter implements Converter<Comment, CommentDTO> {
    @Override
    public CommentDTO convert(Comment from) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(from.getId());
        commentDTO.setCreateDate(from.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        commentDTO.setText(from.getText());
        commentDTO.setUser(new UserDTO(from.getUser().getId(), from.getUser().getFullName()));
        commentDTO.setTask(new TaskDTO(from.getTask().getId(), from.getTask().getHeader()));

        return commentDTO;
    }
}
