package my.edu.utar.socialcookingapp.Model;

public class UserTable {
    private String uid;
    private String name;
    private String email;
    private String phone;
    private String image;

    public UserTable(String uid, String name, String email, String phone, String image) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserTable(){}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
