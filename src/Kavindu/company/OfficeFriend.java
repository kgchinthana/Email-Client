package Kavindu.company;

//import libraries
import java.io.*;
import java.util.ArrayList;

public class OfficeFriend extends RecipientDetails implements Birthdays{

    //to store data for later use
    ArrayList<String> namesOffF = new ArrayList<String>();
    ArrayList<String> emailsOffF = new ArrayList<String>();
    ArrayList<String> birthDaysOffF = new ArrayList<String>();
    ArrayList<String> designationOffF = new ArrayList<String>();

    //attribute
    private String name;
    private String email;
    private String designation;
    private String birthDay;
    private  String date;

    //constructor
    public OfficeFriend(String name,String email,String designation,String birthDay) {
        this.name = name;
        this.email= email;
        this.designation=designation;
        this.birthDay= birthDay;
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
    public void setValue(){
        namesOffF.add(this.name);
        emailsOffF.add(this.email);
        birthDaysOffF.add(this.birthDay);
        designationOffF.add(this.designation);

    }

    //to send birthday wish
    //override the abstract class method
    @Override
    public void setBirthdayWish()  {
        try {
            //read "Birthday Wish Memory.txt" file
            BufferedReader openFileRead = new BufferedReader(new FileReader("Birthday Wish Memory.txt"));
            String line = openFileRead.readLine();


            //for sending birthday greeting
            for (String bDay : birthDaysOffF) {
                if (date.contains(bDay) && (line==null || !date.equals(line))) {
                    String nameBdayObj = namesOffF.get(birthDaysOffF.indexOf(bDay));
                    String emailBdayObj = emailsOffF.get(birthDaysOffF.indexOf(bDay));

                    var send = new SendEmail(emailBdayObj, "Birthday wish", "Wish you a Happy Birthday." + '\n' + "Kavindu",nameBdayObj);
                    MarkBirthdayWishSend mark = new MarkBirthdayWishSend();
                    //to serialize object
                    SerializeDataBase memory = new SerializeDataBase(nameBdayObj,date,"Birthday wish");
                    RecipientEmailMemory obj1 = new RecipientEmailMemory(memory);
                    obj1.serializeEmailDetails();


                }
            }
            openFileRead.close();

        //catch exception
        }catch (FileNotFoundException error1) {
            try {
                FileWriter openWrite = new FileWriter("Birthday Wish Memory.txt");
            } catch (Exception error2) {}
        }
        catch (Exception error3) {
            System.out.println(error3);
        }
    }
}
