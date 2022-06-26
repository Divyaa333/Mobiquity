package Utilities;

public class Common_Utils
{
    public static String Email_Format_Validator(String email)
    {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
         email = "user@domain.com";
        if (email.matches(EMAIL_REGEX))
        {
            return "Valid";
        }
        else
            return "Invalid";
    }
}
