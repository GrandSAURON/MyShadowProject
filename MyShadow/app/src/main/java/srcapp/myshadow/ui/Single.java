package srcapp.myshadow.ui;

public class Single {
    private static final Single INSTANCE = new Single();
    static boolean test = false;
    static boolean mode = false;
    static String filepath;
    static int filecount = 0;

    public Single(){}

    public static Single getInstance(){
        return INSTANCE;
    }
}
