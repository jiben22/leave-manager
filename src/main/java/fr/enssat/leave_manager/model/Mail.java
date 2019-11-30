package fr.enssat.leave_manager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Mail {

    private String from;
    private String to;
    private String subject;
    private Map<String, Object> model;

    public Mail() {

    }


    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}