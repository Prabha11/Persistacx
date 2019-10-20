package persistacx.Query;

import org.boon.Str;

public class QueryService {
    public String getCommaSeparatedStringFromStringArray(String[] array) {
        String value = null;
        int arrayLength = array.length;
        value = array[0];
        for (int i = 1; i < arrayLength; i++) {
            value = String.format("%s,%s",value,array[i]);
        }
        return value;
    }
}
