package functions.postfunc;

public class Posts {
    // 게시글 클래스
    // 제목과 내용 가져오기
    // 제목과 내용 수정하기

    private String title;
    private String text;

    public Posts(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setText(String text) {
        this.text = text;
    }

}
