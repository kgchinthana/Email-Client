package Kavindu.company;

//import libraries
import java.io.*;

public class RecipientEmailMemory {

    //Attribute
    private Object obj;
    private String date;

    //constructor 1
    public RecipientEmailMemory(Object obj) {
        this.obj = obj;
        getDate();
    }

    //constructor 2
    public RecipientEmailMemory() {}


    //get current date
    public void getDate() {
        Today today = new Today();
        this.date = today.getTime();
    }

    //serialization
    public void serializeEmailDetails() throws Exception {

        //modify the given date
        String d[] = date.split("/");
        String modifyDay = d[0] + "-" + d[1] + "-" + d[2];
        String fileName = "details " + modifyDay + ".ser";

        FileOutputStream fout = new FileOutputStream(fileName, true);
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(obj);


        out.flush();
        //closing the stream
        out.close();
    }

    //deserialize
    public void deserializeEmailDetails(String givenDay) {
        try {
            String d[] = givenDay.split("/");
            String modifyDay = d[0] + "-" + d[1] + "-" + d[2];
            String fileName = "details " + modifyDay + ".ser";
            FileInputStream fileObject = new FileInputStream(fileName);

            //Creating stream to read the object
            while (true) {
                ObjectInputStream inObject = new ObjectInputStream(fileObject);
                SerializeDataBase sendObject = (SerializeDataBase) inObject.readObject();

                //display the recipient name and subject of sent
                if (sendObject != null) {
                    System.out.println('\n'+"||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"+'\n');
                    System.out.println("Recipient Name: " + sendObject.name + '\n' + "Subject of Email: " + sendObject.subject );
                    Thread.sleep(500);
                    System.out.println('\n'+"||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"+'\n');
                } else {
                    inObject.close();
                    break;


                }
            }
            fileObject.close();
        //catch exception
        } catch (FileNotFoundException error1) {
            System.out.println("The message was not sent on given day");
        } catch (IOException | ClassNotFoundException |InterruptedException error2) {
        }
    }
}