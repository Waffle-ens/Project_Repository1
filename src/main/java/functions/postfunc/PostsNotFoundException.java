package functions.postfunc;

public class PostsNotFoundException extends RuntimeException {
    public PostsNotFoundException(String message) {
        super(message);
    }
}