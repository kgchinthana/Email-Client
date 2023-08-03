package Kavindu.company;

import java.io.*;
import java.util.ArrayList;

public class Personal extends RecipientDetails implements Birthdays {
    //to store data for later use
    ArrayList<String> namesPer = new ArrayList<String>();
    ArrayList<String> emailsPer = new ArrayList<String>();
    ArrayList<String> birthDaysPer = new ArrayList<String>();
    ArrayList<String> nickNamePer = new ArrayList<String>();

    //attribute
    private String date;
    private String name;
    private String nickName;
    private String email;
    private String birthDay;

    //constructor
    public Personal(String name, String nickName, String email, String birthDay) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.birthDay = birthDay;
        setValue();
        getDate();
        setBirthdayWish();
    }

    //get current time and date
    public void getDate() {
        Today today = new Today();
        this.date = today.getTime();
    }

    //insert values arraylist(setter method)
    public void setValue() {
        namesPer.add(this.name);
        emailsPer.add(this.email);
        birthDaysPer.add(this.birthDay);
        nickNamePer.add(this.nickName);

    }

    //to send birthday wish
    //override the abstract class method
    @Override
    public void setBirthdayWish() {
        try {

            //for sending birthday greeting
            BufferedReader openFileRead = new BufferedReader(new FileReader("Birthday Wish Memory.txt"));
            String line =openFileRead.readLine();

            //for sending birthday greeting
            for (String bDay : birthDaysPer) {
                if (date.contains(bDay) && (line==null || !date.equals(line))) {
                    String nameBdayObj = namesPer.get(birthDaysPer.indexOf(bDay));
                    String emailBdayObj = emailsPer.get(birthDaysPer.indexOf(bDay));
                    var send = new SendEmail(emailBdayObj, "Birthday wish", "hugs and love on your birthday." + '\n' + "Kavindu", nameBdayObj);
                    MarkBirthdayWishSend mark = new MarkBirthdayWishSend();

                    //to serialize object
                    SerializeDataBase memory = new SerializeDataBase(nameBdayObj, date, "Birthday wish");
                    RecipientEmailMemory obj1 = new RecipientEmailMemory(memory);
                    obj1.serializeEmailDetails();


                }

            }

            openFileRead.read();

            //catch exception
        } catch (FileNotFoundException error1) {
            try {
                FileWriter openWrite = new FileWriter("Birthday Wish Memory.txt");
            } catch (Exception error2) {
            }
        } catch (Exception error3) {
            System.out.println(error3);
        }
    }
}

