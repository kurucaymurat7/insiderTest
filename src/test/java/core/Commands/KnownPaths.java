package core.Commands;

import core.Utilities.ConfigurationReader;

public final class KnownPaths {

    private KnownPaths() {
    }
    private static final String SCREENSHOTS = ConfigurationReader.getProperty("screenshots.path");

    public static String getResultScreenShotsFilePath(String fileName) {
        String dirPath =  Path.get(System.getProperty("user.dir"), SCREENSHOTS);
        return Path.get(dirPath, fileName);
    }



}
