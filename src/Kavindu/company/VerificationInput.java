package Kavindu.company;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificationInput {
    /*to verify given input is valid of not*/

    //Attribute
    private String input;

    //constructor
    public VerificationInput(String input) {
        this.input = input;

    }

    public boolean recipientInput(){
        /* verification of recipient input */
        boolean condition = false;


        String valid = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(valid);


        String[] inputArray1 = input.split(",");
        String[] inputArray2 = inputArray1[0].split(": ");
        if(inputArray1[0].contains("Official: ") && inputArray1.length == 3 && inputArray2.length == 2){
            Matcher matcher = pattern.matcher(inputArray1[1]);
            if(matcher.matches()){
                condition = true;
            }
        }
        if(inputArray1[0].contains("Office_friend: ") && inputArray1.length == 4 && inputArray2.length == 2 ) {
            Matcher matcher = pattern.matcher(inputArray1[1]);
            if (matcher.matches()){
                condition = true;
            }
        }
        if(inputArray1[0].contains("Personal: ") && inputArray1.length == 4 && inputArray2.length == 2) {
            Matcher matcher = pattern.matcher(inputArray1[2]);
            if (matcher.matches()){
                condition = true;
            }
        }
        return condition;

    }

    public boolean sendEmailInput(){
        /* Verification of send email */
        boolean condition1 = false;
        try {
            String[] inputArray3 = input.split(",");
            String valid = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(valid);
            Matcher matcher = pattern.matcher(inputArray3[0]);
            if (matcher.matches() && inputArray3.length == 3) {
                condition1 = true;
            }
        }catch (Exception error){
            System.out.println("Invalid input");
        }
        return condition1;

    }

    public  boolean dateInput(){
        boolean condition3 = true;

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            dateFormatter.parse(input);

        } catch (DateTimeParseException error) {
            System.out.println("Invalid date" );
            System.out.println("Try again!!!"+'\n');
            //System.out.println("Enter date again!!!!!!!!"+'\n');
            condition3=false;
        }

        return condition3;
    }

}