package functions.boardfunc;

import functions.postfunc.Posts;

import java.util.*;

public class Boards {
    private String name;
    private List<Posts> posts;

    public Boards(String name) {
        this.name = name;
        this.posts = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void addPost(Posts post) {
        posts.add(post);
    }

}
