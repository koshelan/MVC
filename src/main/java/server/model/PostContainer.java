package server.model;

public class PostContainer {
    private boolean removed;
    private Post post;

    public PostContainer(Post post) {
        this.removed = false;
        this.post = post;
    }

    public void setRemoved() {
        this.removed = true;
    }

    public void setNotRemoved() {
        this.removed = false;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean isRemoved() {
        return removed;
    }

    public Post getPost() {
        return post;
    }
}
