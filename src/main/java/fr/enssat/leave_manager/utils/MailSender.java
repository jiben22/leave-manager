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

import java.util.Map;

@Slf4j
public class MailSender {

    public static boolean sendMail(Map<String, String> mailContent) {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient("04f169a80cfa6b1d92ddd1b298925b1e", "890ffa85010c0c52a309aa190084528d");

        request = new MailjetRequest(Email.resource)
                .property(Email.FROMEMAIL, "leavemanagerjee@outlook.com")
                .property(Email.FROMNAME, "Application de gestion des congés")
                .property(Email.SUBJECT, mailContent.get("subject"))
                .property(Email.RECIPIENTS, new JSONArray()
                        .put(new JSONObject()
                                .put("Email", mailContent.get("recipient"))))
                .property(Email.MJTEMPLATEID, Integer.valueOf(mailContent.get("templateId")))
                .property(Email.MJTEMPLATELANGUAGE, true)
                .property(Email.VARS, new JSONObject()
                        .put("firstname", mailContent.get("firstname"))
                        .put("resetUrl", mailContent.get("resetUrl"))
                        .put("subject", mailContent.get("subject")));

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
