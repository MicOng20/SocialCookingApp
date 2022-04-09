package my.edu.utar.socialcookingapp.Model;

public class UserTable {
    private String id;
    private String username;
    private String bio;
    private String profileImageURL;

    public UserTable(String id, String username, String bio, String profileImageURL) {
        this.id = id;
        this.username = username;
        this.bio = bio;
        this.profileImageURL = profileImageURL;
    }

    public UserTable(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }
}
