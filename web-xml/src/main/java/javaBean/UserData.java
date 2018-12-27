package javaBean;

public class UserData {
    /**
     * 学号
     */
    private String userID;
    /**
     * 密码
     */
    private String password;
    /**
     * 学生姓名
     */
    private String userName;
    /**
     * 创建时间（时间戳）
     */
    private long createTimeStamp;
    /**
     * 更新时间（时间戳）
     */
    private  long updateTimeStamp;
    /**
     * 学院
     */
    private String college;
    /**
     * 部门
     */
    private String department;
    /**
     * 专业
     */
    private String major;
    /**
     *注册渠道（0 pc，1 wx）
     */
    private int channel;
    /**
     * 学生状态（0启用，1禁用，2黑名单）
     */
    private int state;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(long createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public long getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(long updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean equals(UserData b) {
        return userID.equals(b.userID);
    }
}
