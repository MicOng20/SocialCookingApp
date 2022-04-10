package my.edu.utar.socialcookingapp.Model;

public class PostTable {
    private String postID;
    private String postImage;
    private String caption;
    private String publisher;

    public PostTable(String postID, String postImage, String caption, String publisher) {
        this.postID = postID;
        this.postImage = postImage;
        this.caption = caption;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
