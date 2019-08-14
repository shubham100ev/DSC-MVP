package tech.dsckiet.Events;

public class ModelEvent {

    public String mTitle, mTime,mVenue;

    public ModelEvent() {

    }

    public ModelEvent(String mTitle,String mTime,String mVenue){
        this.mTitle = mTitle;
        this.mTime = mTime;
        this.mVenue = mVenue;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmVenue() {
        return mVenue;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmVenue(String mVenue) {
        this.mVenue = mVenue;
    }
}
