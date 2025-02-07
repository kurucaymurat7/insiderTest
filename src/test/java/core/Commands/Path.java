package core.Commands;

import java.io.File;

public final class Path {

    public static String get(String arg1, String arg2) {
        return getPath(arg1) + File.separator + getPath(arg2);
    }

    public static String get(String arg1, String arg2, String arg3) {
        return getPath(arg1) + File.separator + getPath(arg2) + File.separator + getPath(arg3);
    }

    private static String getPath(String res) {
        if (res == null) return null;

        if (File.separatorChar == '\\') {
            // From Windows to Linux/Mac
            return res.replace('/', File.separatorChar);
        }

        // From Linux/Mac to Windows
        return res.replace('\\', File.separatorChar);
    }

}
