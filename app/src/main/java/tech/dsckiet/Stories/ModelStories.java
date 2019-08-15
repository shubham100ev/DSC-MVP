package tech.dsckiet.Stories;

public class ModelStories {

    public String mTitle, mDesc;

    public ModelStories() {

    }

    public ModelStories(String mTitle, String mDesc) {
        this.mTitle = mTitle;
        this.mDesc = mDesc;
    }


    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

}
