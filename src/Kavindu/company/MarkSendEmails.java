package Kavindu.company;

import java.io.Serializable;

public class MarkSendEmails implements Serializable {

    //Attribute
    protected String name;
    protected String email;
    protected  String birthday;

    private String jsonResult;

    public MarkSendEmails(String name, String email, String birthday) {
        this.name =name;
        this.email = email;
        this.birthday = birthday;
    }
}
