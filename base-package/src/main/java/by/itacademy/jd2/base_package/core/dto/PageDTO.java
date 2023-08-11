package by.itacademy.jd2.base_package.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDTO<T> {
    private Integer number;
    private Integer size;
    private Integer totalPages;
    private Long totalElements;
    private Boolean first;
    private Integer numberOfElements;
    private Boolean last;
    private List<T> content;
}
