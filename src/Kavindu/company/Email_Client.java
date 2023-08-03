package Kavindu.company;

// your index number
//200093M

//name
//CHINTHANA S.K.G.

//import libraries
import java.io.*;
import java.util.*;

public class Email_Client {

    //create statics member get recipient count
    public static int countOfRecipientObject=0;

    //main method
    public static void main(String[] args) throws IOException {

        try {
            //get current time and date
            Today date = new Today();
            String day = date.getTime();

            try {
                //Read the ClientList database and send the birthday wish
                BufferedReader openFileRead = new BufferedReader(new FileReader("clientList.txt"));
                String readValues;

                boolean condition = false;

                //read client file line by line
                while ((readValues = openFileRead.readLine()) != null) {
                    RecipientFactory recipientFactory = new RecipientFactory();
                    countOfRecipientObject++;
                    RecipientDetails recipientDetails = recipientFactory.recipientFactorySet(readValues);
                    condition =true;
                }
                openFileRead.close();



                if(condition == true && MarkBirthdayWishSend.condition) {
                    //create file to prevent sending birthday
                    BufferedWriter writeFile = new BufferedWriter(new FileWriter("Birthday Wish Memory.txt"));
                    writeFile.write(day);
                    writeFile.close();
                }

            }
            //catch exception
            catch (FileNotFoundException error2) {
                System.out.println("Not found such file file");
                BufferedWriter writeFile = new BufferedWriter(new FileWriter("clientList.txt", true));
                System.out.println("New clientList.txt file was created");
            }


            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter option type: \n"
                        + "1 - Adding a new recipient\n"
                        + "2 - Sending an email\n"
                        + "3 - Printing out all the recipients who have birthdays\n"
                        + "4 - Printing out details of all the emails sent\n"
                        + "5 - Printing out the number of recipient objects in the application\n"
                        + "6 - Exit");

                System.out.println('\n'+"==================================================================================================");
                System.out.println("=========================================START PROJECT============================================");
                System.out.println("=================================================================================================="+'\n');

                System.out.println("==================================================================================================");
                System.out.print("Enter your choice: ");
                int option = scanner.nextInt();
                System.out.println("==================================================================================================");


                //for removing invalid input
                if(option<7) {
                    switch (option) {
                        case 1:
                            // input format - Official: nimal,nimal@gmail.com,ceo
                            // Use a single input to get all the details of a recipient
                            // code to add a new recipient
                            // store details in clientList.txt file
                            // Hint: use methods for reading and writing files
                            //to exception handling
                            try {

                                System.out.println("Official: nimal,nimal@gmail.com,ceo" + '\n' +
                                        "Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12" + '\n' +
                                        "Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10" + '\n');
                                System.out.println("if you want to stop the object input through command prompt, {  press '-1' }");

                                //Open file and create object to store the detail
                                BufferedWriter writeFile2 = new BufferedWriter(new FileWriter("clientList.txt", true));
                                System.out.println("==================================================================================================");
                                System.out.println("Add your data entries: ");

                                //loop for read input
                                while (true) {

                                    //get initial input through command-line options
                                    Scanner scanner1 = new Scanner(System.in);
                                    String input = scanner1.nextLine();

                                    //for break loop
                                    if (!input.contains("-1")) {
                                        VerificationInput valid = new VerificationInput(input);
                                        if (valid.recipientInput()) {
                                            writeFile2.write(input + '\n');
                                            countOfRecipientObject++;
                                        } else {
                                            System.out.println("Given input invalid");
                                            System.out.println("Add your data entries again!!!!!!!!" + '\n');
                                            System.out.println("Add your data entries: ");
                                            continue;
                                        }

                                    } else {
                                        break;
                                    }
                                }
                                writeFile2.close();

                                System.out.println("==================================================================================================");

                                //catch exception
                            } catch (Exception error) {
                                System.out.println(error);
                            }

                            break;
                        case 2:
                            // input format - email, subject, content
                            // code to send an email
                            System.out.println("==================================================================================================");
                            try {
                                //arrange initial value for send email
                                System.out.println("input format - email, subject, content");
                                Scanner scanner2 = new Scanner(System.in);
                                String input1 = scanner2.nextLine();
                                String[] values = input1.split(",");
                                String email = values[0];
                                String subject = values[1];
                                String content = values[2];

                                //verify the input
                                VerificationInput valid1 = new VerificationInput(input1);
                                if (valid1.sendEmailInput()) {
                                    RecipientFileRead read = new RecipientFileRead();
                                    String recipientName;
                                    if((recipientName=read.checkRecipientsExist(email)) instanceof String){
                                        //crete object to send mail
                                        var send = new SendEmail(email, subject, content,recipientName);
                                        //to serialize object
                                        SerializeDataBase memory = new SerializeDataBase(recipientName,day,subject);
                                        RecipientEmailMemory obj = new RecipientEmailMemory(memory);
                                        obj.serializeEmailDetails();
                                    }

                                    else {
                                        //optional input for later use
                                        System.out.print("Enter the User Name for later use:  ");
                                        String name = scanner2.nextLine();

                                        //crete object to send mail
                                        var send = new SendEmail(email, subject, content, name);
                                        //to serialize object
                                        SerializeDataBase memory = new SerializeDataBase(name,day,subject);
                                        RecipientEmailMemory obj = new RecipientEmailMemory(memory);
                                        obj.serializeEmailDetails();
                                    }

                                } else {
                                    System.out.println("Given input invalid" + '\n');
                                    break;
                                }

                                System.out.println("==================================================================================================");
                                //Catch exception
                            } catch (Exception error2) {
                                System.out.println("Given input invalid" + '\n');
                            }
                            break;
                        case 3:
                            // input format - yyyy/MM/dd (ex: 2018/09/17)
                            // code to print recipients who have birthdays on the given date
                            System.out.println("==================================================================================================");
                            //get initial input through command-line options
                            System.out.println("input format - yyyy/MM/dd (ex: 2022/08/12)");
                            Scanner scan = new Scanner(System.in);
                            String givenDate = scan.nextLine();

                            VerificationInput valid3 = new VerificationInput(givenDate);
                            if (valid3.dateInput()) {
                                String[] dates = givenDate.split("/");

                                //rearrange the given date
                                String modifyDate = dates[1] + "/" + dates[2];

                                //create object to get name of recipients birthday on given day
                                RecipientFileRead detail = new RecipientFileRead();
                                detail.getRecipientOfGivenBirthday(modifyDate);

                            } else {
                                break;
                            }

                            break;
                        case 4:

                            // input format - yyyy/MM/dd (ex: 2018/09/17)
                            // code to print the details of all the emails sent on the input date
                            System.out.println("==================================================================================================");
                            //get initial input through command-line options
                            System.out.println("input format - yyyy/MM/dd (ex: 2022/08/12)");
                            Scanner scanObj = new Scanner(System.in);
                            String givenDay = scanObj.nextLine();
                            VerificationInput valid4 = new VerificationInput(givenDay);

                            if (valid4.dateInput()) {
                                //create object to get details of E-mail
                                RecipientEmailMemory obj = new RecipientEmailMemory();
                                obj.deserializeEmailDetails(givenDay);

                            }

                            break;

                        case 5:
                            // code to print the number of recipient objects in the application
                            //create statics member for get count of recipient
                            System.out.println("==================================================================================================");
                            System.out.print("number of recipient objects in application: ");

                            //use statics members to get count of recipients in application
                            System.out.println(Email_Client.countOfRecipientObject);
                            System.out.println("==================================================================================================");
                            break;

                        case 6:
                            System.out.println("==================================================================================================");
                            System.out.println("======================================= PROJECT WAS ENDED ========================================");
                            System.out.println("==================================================================================================");
                            System.exit(0);
                            break;
                    }
                }
                else {
                    System.out.println("Invalid input,Therefore programme is ended");
                    System.exit(0);
                }

                // start email client
                // code to create objects for each recipient in clientList.txt
                // use necessary variables, methods and classes
            }

        //catch exception
        }catch (InputMismatchException error1){
            System.out.println("Invalid input,Therefore programme is ended");
            System.exit(0);
        }
        catch (Exception error2){
            System.out.println(error2);
        }
    }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)