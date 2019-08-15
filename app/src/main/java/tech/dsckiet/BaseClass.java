package tech.dsckiet;

public class BaseClass {

    private static BaseClass mInstance = null;

    public String BASE_URL = "http://www.dsckiet.tech";
    public String BASE_URL_EVENTS = "http://www.dsckiet.tech/api/v1/events";
    public String BASE_URL_STORIES = "http://www.dsckiet.tech/api/v1/about";
    public String BASE_URL_TEAM = "http://www.dsckiet.tech/api/v1/team";
    public String BASE_URL_PROJECT = "http://www.dsckiet.tech/api/v1/ideas";




    protected BaseClass() {

    }

    public static synchronized BaseClass getInstance() {
        if (null == mInstance) {
            mInstance = new BaseClass();
        }
        return mInstance;
    }
}
