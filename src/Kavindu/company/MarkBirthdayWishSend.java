package Kavindu.company;

public class MarkBirthdayWishSend {
    /*check birthday wishes already send*/
    static boolean condition =false;

    //Mark as birthday wishes are sent
    public MarkBirthdayWishSend(){
        this.condition = true;
    }
    //statics member to get reference birthday wishes are already sent
    public static boolean getCondition(){
        return condition;

    }
}