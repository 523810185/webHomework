package javaBean;

public class QueryData {
    private String userID;
    private String userName;
    private String state;
    private int channel;
    private long createTimeUpStamp;
    private long createTimeDownStamp;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public long getCreateTimeUpStamp() {
        return createTimeUpStamp;
    }

    public void setCreateTimeUpStamp(long createTimeUpStamp) {
        this.createTimeUpStamp = createTimeUpStamp;
    }

    public long getCreateTimeDownStamp() {
        return createTimeDownStamp;
    }

    public void setCreateTimeDownStamp(long createTimeDownStamp) {
        this.createTimeDownStamp = createTimeDownStamp;
    }
}
