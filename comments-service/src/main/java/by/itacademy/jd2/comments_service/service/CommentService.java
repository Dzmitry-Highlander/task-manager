package by.itacademy.jd2.comments_service.service;

import by.itacademy.jd2.comments_service.core.dto.CommentCreateDTO;
import by.itacademy.jd2.comments_service.dao.api.ICommentDao;
import by.itacademy.jd2.comments_service.dao.entity.Comment;
import by.itacademy.jd2.comments_service.service.api.ICommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService implements ICommentService {
    private final ICommentDao commentDao;

    public CommentService(ICommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public Comment create(CommentCreateDTO item) {
        Comment comment = new Comment();
        LocalDateTime createDate = LocalDateTime.now();

        comment.setCreateDate(createDate);
        comment.setText(item.getText());

        return commentDao.save(comment);
    }

    @Override
    public Comment read(UUID id) {
        return commentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NO NO NO!"));
    }

    @Override
    public List<Comment> read() {
        return commentDao.findAll();
    }

    @Override
    public void delete(UUID id) {
        commentDao.deleteById(id);
    }
}
