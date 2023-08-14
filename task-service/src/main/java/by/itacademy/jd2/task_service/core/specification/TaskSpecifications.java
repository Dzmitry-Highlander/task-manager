package by.itacademy.jd2.task_service.core.specification;

import by.itacademy.jd2.task_service.core.dto.ProjectRefDTO;
import by.itacademy.jd2.task_service.core.dto.UserRefDTO;
import by.itacademy.jd2.task_service.core.enums.ETaskStatus;
import by.itacademy.jd2.task_service.dao.entity.Task;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class TaskSpecifications {
    public static Specification<Task> byStatus(List<ETaskStatus> status) {
        return (root, query, builder) ->
                builder.in(root.get("status")).value(status);
    }
    public static Specification<Task> byImplementer(List<UserRefDTO> implementer) {
        List<UUID> uuids = implementer.stream().map(UserRefDTO::getUuid).toList();
        return (root, query, builder) ->
                builder.in(root.get("implementer")).value(uuids);
    }
    public static Specification<Task> byProject(List<ProjectRefDTO> project) {
        List<UUID> uuids = project.stream().map(ProjectRefDTO::getUuid).toList();
        return (root, query, builder) ->
                builder.in(root.get("project").get("uuid")).value(uuids);
    }
}
