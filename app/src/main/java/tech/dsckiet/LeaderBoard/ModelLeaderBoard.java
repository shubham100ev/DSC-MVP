package tech.dsckiet.LeaderBoard;

public class ModelLeaderBoard {

    public String mRank,mHandle,mScore;

    public ModelLeaderBoard() {

    }

    public ModelLeaderBoard(String rank,String score,String handle){
        this.mRank = rank;
        this.mScore = score;
        this.mHandle =  handle;
    }

    public String getmHandle() {
        return mHandle;
    }

    public String getmRank() {
        return mRank;
    }

    public String getmScore() {
        return mScore;
    }
}
