package my.edu.utar.socialcookingapp.Model;

public class PostTable {
    private String postID;
    private String postImage;
    private String description;
    private String publisher;

    public PostTable(String postID, String postImage, String description, String publisher) {
        this.postID = postID;
        this.postImage = postImage;
        this.description = description;
        this.publisher = publisher;
    }

    public PostTable(){
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
