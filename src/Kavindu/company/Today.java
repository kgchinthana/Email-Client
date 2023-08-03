package Kavindu.company;

//import libraries
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Today {
    /*set the current date and time*/
    public String getTime(){

        //to create object get date standard format
        DateTimeFormatter today = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        //return current date
        return today.format(now);
    }

}
