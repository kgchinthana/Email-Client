package Kavindu.company;

import java.io.FileReader;
import java.util.Scanner;

public class RecipientFileRead  {
    //Attribute
    private int countBDays=0;
    private String name;
    private String bDay;


    //display recipient who has birthday given day
    public void getRecipientOfGivenBirthday(String modifyDate) throws Exception{

        //read the file
        FileReader openFileRead = new FileReader("clientList.txt");
        Scanner fileReader = new Scanner(openFileRead);
        while (fileReader.hasNextLine()) {
            String readValues1 = fileReader.nextLine();
            if(!readValues1.contains("Official:")){
                String[] object = readValues1.split(",");
                bDay = object[3];
                name = object[0].split(": ")[1];

                if(bDay.contains(modifyDate)){
                    countBDays+=1;
                    Thread.sleep(500);
                    System.out.println(name);
                }
            }
        }
        fileReader.close();
        System.out.println('\n'+"***********************************************************************************"+'\n');

    }

    public String checkRecipientsExist(String email) throws Exception{

        FileReader openFileRead = new FileReader("clientList.txt");
        Scanner fileReader = new Scanner(openFileRead);
        while (fileReader.hasNextLine()) {
            String readValues1 = fileReader.nextLine();
            String[] object = readValues1.split(",");
            if (readValues1.contains("Official: ") && object[1].contains(email)) {
                this.name = object[0].split(": ")[1];
            }
            if (readValues1.contains("Office_friend: ") && object[1].contains(email)) {
                this.name = object[0].split(": ")[1];
            }
            if (readValues1.contains("Personal: ") && object[2].contains(email)) {
                this.name = object[0].split("")[1];
            }
        }

        return name;
    }
    public int countOfBirthdays(){
        return countBDays;
    }


}
