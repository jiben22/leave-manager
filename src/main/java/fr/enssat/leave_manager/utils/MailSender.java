package fr.enssat.leave_manager.utils;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Email;
import com.mailjet.client.resource.Emailv31;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
public class MailSender {

    public static boolean send(String subject, String body) {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(System.getenv("API_KEY"), System.getenv("API_KEY_SECRET"));
        request = new MailjetRequest(Email.resource)
                .property(Email.FROMEMAIL, "leavemanagerjee@outlook.com")
                .property(Email.FROMNAME, "Application de gestion des congés")
                .property(Email.SUBJECT, subject)
                .property(Email.TEXTPART, body)
                //.property(Email.HTMLPART, "<h3>Dear passenger, welcome to <a href=\"https://www.mailjet.com/\">Mailjet</a>!<br />May the delivery force be with you!")
                .property(Email.RECIPIENTS, new JSONArray()
                        .put(new JSONObject()
                                .put("Email", "lucasrollin@yahoo.com")));

        try {
            response = client.post(request);
            System.out.println(response.getStatus());
            System.out.println(response.getData());
            return response.getStatus() == 200;

        } catch(Exception e) {
            log.error(e.toString());
        }

        return false;




    }
}
