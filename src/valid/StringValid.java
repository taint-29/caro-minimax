package valid;

public class StringValid {
    public Boolean checkLength(String value){
        return value.length() <= 20;
    }

    public Boolean notBlank(String value, String value2) {
        if (value.equals("") == false && value != null && value2.equals("") == false && value2 != null) {
            return true;
        }
        return false;
    }
}
