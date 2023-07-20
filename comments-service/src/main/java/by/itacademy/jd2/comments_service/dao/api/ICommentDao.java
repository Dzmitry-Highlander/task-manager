package by.itacademy.jd2.comments_service.dao.api;

import by.itacademy.jd2.dao.entity.Comment;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface ICommentDao extends ListCrudRepository<Comment, UUID> {
}
