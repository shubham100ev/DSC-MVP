package tech.dsckiet;

public class BaseClass {

    private static BaseClass mInstance = null;

    public String BASE_URL_EVENTS = "http://www.dsckiet.tech/api/v1/events";



    protected BaseClass() {

    }

    public static synchronized BaseClass getInstance() {
        if (null == mInstance) {
            mInstance = new BaseClass();
        }
        return mInstance;
    }
}
