package my.edu.utar.socialcookingapp.Model;

import my.edu.utar.socialcookingapp.Adapter.CommentAdapter;

public class CommentTable {
    private String comment;
    private String publisher;

    public CommentTable(String comment, String publisher) {
        this.comment = comment;
        this.publisher = publisher;
    }

    public CommentTable(){}

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
