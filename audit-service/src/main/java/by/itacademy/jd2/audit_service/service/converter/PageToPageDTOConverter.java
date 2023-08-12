package by.itacademy.jd2.audit_service.service.converter;

import by.itacademy.jd2.base_package.core.dto.PageDTO;
import by.itacademy.jd2.audit_service.core.dto.AuditDTO;
import by.itacademy.jd2.audit_service.dao.entity.Audit;
import by.itacademy.jd2.base_package.core.dto.UserShortDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PageToPageDTOConverter implements Converter<Page<Audit>, PageDTO<AuditDTO>> {
    @Override
    public PageDTO<AuditDTO> convert(Page<Audit> page) {
        List<AuditDTO> auditDTOS = new ArrayList<>();

        for (Audit entity : page.getContent()) {
            AuditDTO auditDTO = new AuditDTO();
            UserShortDTO userShortDTO = new UserShortDTO();
            userShortDTO.setUuid(entity.getUuid());
            userShortDTO.setEmail(entity.getEmail());
            userShortDTO.setFio(entity.getFio());
            userShortDTO.setRole(entity.getRole());
            auditDTO.setUuid(entity.getUuid());
            auditDTO.setCreateDate(entity.getCreateDate()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli());
            auditDTO.setUser(userShortDTO);
            auditDTO.setText(entity.getText());
            auditDTO.setType(entity.getType());
            auditDTO.setId(entity.getId());
            auditDTOS.add(auditDTO);
        }

        PageDTO<AuditDTO> pageDTO = new PageDTO<>();
        pageDTO.setNumber(page.getNumber());
        pageDTO.setSize(page.getSize());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        pageDTO.setFirst(page.isFirst());
        pageDTO.setNumberOfElements(page.getNumberOfElements());
        pageDTO.setLast(page.isLast());
        pageDTO.setContent(auditDTOS);

        return pageDTO;
    }
}
