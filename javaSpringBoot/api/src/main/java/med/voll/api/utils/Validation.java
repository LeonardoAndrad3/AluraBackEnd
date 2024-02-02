package med.voll.api.utils;

public class Validation {

    public static boolean isNull(String str){
        return (str == null || str.equalsIgnoreCase(""));
    }

}
