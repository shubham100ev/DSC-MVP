package tech.dsckiet;

public class BaseClass {

    private static BaseClass mInstance = null;

    public String BASE_URL = "http://www.dsckiet.tech/api/v1";
    public void setBASE_URL(String baseUrl){
        BASE_URL += baseUrl;
    }

    public String getBASE_URL(){
        return BASE_URL;
    }

    protected BaseClass() {

    }

    public static synchronized BaseClass getInstance() {
        if (null == mInstance) {
            mInstance = new BaseClass();
        }
        return mInstance;
    }
}
