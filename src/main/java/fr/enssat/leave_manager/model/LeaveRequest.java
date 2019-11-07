package fr.enssat.leave_manager.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class LeaveRequest {
    @Size(max = 255, message = "Comment should be maximum 255 characters")
    private String reason;
    @FutureOrPresent
    private LocalDateTime creation = LocalDateTime.now();
    @PastOrPresent
    private LocalDateTime last_edition = LocalDateTime.now();
    @NotNull
    @FutureOrPresent
    private LocalDateTime starting_date;
    @NotNull
    @FutureOrPresent
    private LocalDateTime ending_date;
    @Size(max = 255, message = "Comment should be maximum 255 characters")
    private String hr_comment;
    private Employee eid;
    private TypeOfLeave type_id;
    @Size(min = 33, max = 33)
    private String lrid;

    public LeaveRequest(@Size(max = 255, message = "Comment should be maximum 255 characters") String reason, @FutureOrPresent LocalDateTime creation, @PastOrPresent LocalDateTime last_edition, @NotNull @FutureOrPresent LocalDateTime starting_date, @NotNull @FutureOrPresent LocalDateTime ending_date, @Size(max = 255, message = "Comment should be maximum 255 characters") String hr_comment, Employee eid, TypeOfLeave type_id) {
        this.reason = reason;
        this.creation = creation;
        this.last_edition = last_edition;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
        this.hr_comment = hr_comment;
        this.eid = eid;
        this.type_id = type_id;
    }

    @NotNull
    private enum status {PENDING, CANCELLED, ACCEPTED, DECLINED}
}
