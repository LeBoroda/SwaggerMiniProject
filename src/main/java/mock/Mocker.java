package mock;

public final class Mocker {

    private static boolean isMocked = false;

    private Mocker(final boolean isMocked) {
        Mocker.isMocked = isMocked;
    }

    public static boolean isMocked() {
        return Mocker.isMocked;
    }

    public static void setIsMocked(final String argument) {
        boolean mockStatus = Boolean.parseBoolean(argument);
        Mocker.isMocked = mockStatus;
    }

    public static String getUrl() {
        return isMocked ? "http://localhost:18389" : "https://petstore.swagger.io/v2";
    }

}
