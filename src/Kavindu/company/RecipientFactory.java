package Kavindu.company;

public class RecipientFactory {
    //attribute
    private String detaial;
    private String[] detaials;
    private String status;
    private String[] personDetails;
    private String[] birth;
    private  String bDay;

    //create factory design pattern
    public RecipientDetails recipientFactorySet(String detaial) {
        this.detaial = detaial;
        detaials = detaial.split(": ");
        status = detaials[0];
        personDetails = detaials[1].split(",");


        //details of Official recipient
        if (status.equals("Official")) {
            return new Official(personDetails[0], personDetails[1], personDetails[2]);

        }

        //details of Office_friend recipient
        if (status.equals("Office_friend")) {
            birth = personDetails[3].split("/");
            bDay = birth[1] + "/" + birth[2];

            return new OfficeFriend(personDetails[0], personDetails[1], personDetails[2], bDay);

        }

        //details of personal recipient
        if (status.equals("Personal")) {
            birth = personDetails[3].split("/");
            bDay = birth[1] + "/" + birth[2];
            return new Personal(personDetails[0], personDetails[1], personDetails[2], bDay);

        }
        return null;
    }

}


