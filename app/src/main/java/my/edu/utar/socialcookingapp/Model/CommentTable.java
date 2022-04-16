package my.edu.utar.socialcookingapp.Model;

import my.edu.utar.socialcookingapp.Adapter.CommentAdapter;

public class CommentTable {
    private String comment;
    private String publisher;
    private String commentid;

    public CommentTable(String comment, String publisher, String commentid) {
        this.comment = comment;
        this.publisher = publisher;
        this.commentid = commentid;
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

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }
}
