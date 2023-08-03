package Kavindu.company;

import java.util.ArrayList;

public class Official  extends RecipientDetails{

    //attribute
    private String name;
    private String email;
    private String designation;

    //to store data for later use
    ArrayList<String> namesOff = new ArrayList<String>();
    ArrayList<String> emailsOff = new ArrayList<String>();
    ArrayList<String> designationOff = new ArrayList<String>();

    //constructor
    public Official(String name,String email,String designation) {
        this.name = name;
        this.email= email;
        this.designation=designation;
        setValue();
    }

    //insert values arraylist(setter method)
    public void setValue(){
        namesOff.add(this.name);
        emailsOff.add(this.email);
        designationOff.add(this.designation);


    }

}
