package Kavindu.company;

import java.io.Serializable;

public class SerializeDataBase implements Serializable {
    protected String name;
    protected String date;
    protected String subject;

    public SerializeDataBase(String name,String date,String subject){
        this.name = name;
        this.date = date;
        this.subject = subject;
    }
}
