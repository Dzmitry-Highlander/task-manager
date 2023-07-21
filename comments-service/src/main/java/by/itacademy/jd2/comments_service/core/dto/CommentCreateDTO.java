package by.itacademy.jd2.comments_service.core.dto;

public class CommentCreateDTO {
    private Long createDate;
    private String text;

    public CommentCreateDTO() {
    }

    public CommentCreateDTO(Long createDate, String text) {
        this.createDate = createDate;
        this.text = text;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
