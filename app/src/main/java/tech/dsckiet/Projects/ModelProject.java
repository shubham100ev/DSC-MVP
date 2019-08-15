package tech.dsckiet.Projects;

public class ModelProject {

    public String mTitle,mDesc;




    public ModelProject() {

    }

    public ModelProject(String title,String desc){
        this.mTitle = title;
        this.mDesc = desc;

    }

    public String getmDesc() {
        return mDesc;
    }

    public String getmTitle() {
        return mTitle;
    }
}
