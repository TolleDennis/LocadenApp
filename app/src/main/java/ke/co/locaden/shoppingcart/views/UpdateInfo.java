package ke.co.locaden.shoppingcart.views;

public class UpdateInfo {

    String userId;
    String userFullName;
    String userAge;

    public UpdateInfo(){

    }

    public UpdateInfo(String userId, String userFullName, String userAge) {
        this.userId = userId;
        this.userFullName = userFullName;
        this.userAge = userAge;
    }

    public UpdateInfo(String id, String name, String usernam, String ageUser) {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}
