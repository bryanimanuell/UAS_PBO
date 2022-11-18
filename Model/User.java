package UAS.Model;

public class User {
    int userID;
    String userName;
    String password;
    String userEmail;
    String userGender;
    CategoryUser userCategory;
    int userFollowers;

    public User() {

    }

    public User(int userID, String userName, String password, String userEmail, String userGender,
            CategoryUser userCategory,
            int userFollowers) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userCategory = userCategory;
        this.userFollowers = userFollowers;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public CategoryUser getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(CategoryUser userCategory) {
        this.userCategory = userCategory;
    }

    public int getUserFollowers() {
        return userFollowers;
    }

    public void setUserFollowers(int userFollowers) {
        this.userFollowers = userFollowers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
