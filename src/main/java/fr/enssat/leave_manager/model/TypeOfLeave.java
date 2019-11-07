package fr.enssat.leave_manager.model;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TypeOfLeave {
    @Size(min = 1, max = 45, message = "Comment should be maximum 45 characters")
    private String text;
    @Size(max = 255, message = "Comment should be maximum 255 characters")
    private String desc;
    private Boolean is_archived = false;
    @Size(min = 32, max = 32)
    private String id;

    public TypeOfLeave(@Size(min = 1, max = 45, message = "Comment should be maximum 45 characters") String text, @Size(max = 255, message = "Comment should be maximum 255 characters") String desc, Boolean is_archived) {
        this.text = text;
        this.desc = desc;
        this.is_archived = is_archived;
    }
}
