package io.github.pepsidawg.mapbuddy.utilities;

public class UUIDUtil {

    static public String toLongForm(String uuid) {
        if(uuid.length() == 32)
            return uuid.substring(0,8) + "-"
                    + uuid.substring(8,12) + "-"
                    + uuid.substring(12,16) + "-"
                    + uuid.substring(16,20) + "-"
                    + uuid.substring(20);
        else
            return uuid;
    }

    static public String toShortForm(String uuid) {
        return uuid.replace("-","");
    }
}
