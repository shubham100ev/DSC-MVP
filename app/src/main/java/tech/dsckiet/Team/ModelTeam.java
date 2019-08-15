package tech.dsckiet.Team;

public class ModelTeam {
//
//    String email = arr.getJSONObject(i).getString("email");
//    String linkedin = arr.getJSONObject(i).getString("linkedin");
//    String github = arr.getJSONObject(i).getString("github");
//    String twitter = arr.getJSONObject(i).getString("twitter");
//    String website = arr.getJSONObject(i).getString("website");
    public String mName, mRole,mImage,mEmail,mLinkedin,mGithub,mTwitter,mWebsite;

    private boolean expanded;

    public ModelTeam() {

    }

    public ModelTeam(String mName, String mRole, String mImage,String mEmail,String mLinkedin,String mGithub,String mTwitter,String mWebsite){
        this.mImage= mImage;
        this.mName = mName;
        this.mRole = mRole;
        this.mEmail = mEmail;
        this.mLinkedin = mLinkedin;
        this.mGithub = mGithub;
        this.mTwitter = mTwitter;
        this.mWebsite = mWebsite;
    }

    public String getmName() {
        return mName;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmRole() {
        return mRole;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmGithub() {
        return mGithub;
    }

    public String getmLinkedin() {
        return mLinkedin;
    }

    public String getmTwitter() {
        return mTwitter;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
