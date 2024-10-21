package functions.postfunc;

import functions.CommonFunctions;
import functions.UrlNotFoundException;
import functions.UrlPram;

import java.util.*;

public class PostFunctions extends CommonFunctions implements PostFunctionsInterface {

    private List<Posts> postsList;
    Scanner scanner = new Scanner(System.in); //url 기능을 추가면서 scanner 객체가 필요해짐

    public PostFunctions() { // 생성자
        postsList = new ArrayList<>();
    }


    @Override
    public void parsingUrl(String url) throws UrlNotFoundException {


            //기존의 중복코드 CommonFunctions 에 재정의
            ParsedUrl parsedUrl = CommonFunctions.parseUrl(url);
            String actions = parsedUrl.getAction();
            List<UrlPram> params = parsedUrl.getParams();

            switch (actions) { // 파싱한 url 파라미터 기능 구현부
                case "create":
                    createPost(params);
                    break;
                case "read":
                    readPost(params);
                    break;
                case "delete":
                    deletePost(params);
                    break;
                case "update":
                    updatePost(params);
                    break;
                case "list":
                    listPosts();
                    break;
                default: // 잘못된 url 기능 예외처리
                    throw new UrlNotFoundException(actions + " (은)는 유효한 기능이 아닙니다.");
            }

    }

    @Override
    public void createPost(List<UrlPram> params) {

        System.out.print("제목 : ");
        String title = scanner.nextLine();
        System.out.print("내용 : ");
        String text = scanner.nextLine();

        Posts post = new Posts(title, text);
        postsList.add(post);
        System.out.println("포스팅 완료");

    }


    @Override
    public void readPost(List<UrlPram> params) {

        if (postsList.isEmpty()) {
            System.out.println("조회할 포스트가 존재하지 않습니다.");
            return;
        }

        String postId = getParamValue(params, "postId"); // 이전의 scanner 대체

        if(postId == null) {
            System.out.println("postId가 제공되어야 합니다.");
            return;
        }

        try {
            int pId = Integer.parseInt(postId);
            if (pId > 0 && pId <= postsList.size()) {
                Posts selectedPost = postsList.get(pId - 1);
                System.out.println(pId + "번 포스트");
                System.out.println("제목 : " + selectedPost.getTitle());
                System.out.println("내용 : " + selectedPost.getText());
            } else {
                System.out.println("잘못된 포스트 번호 입니다.");
            }
        } catch ( NumberFormatException e ) {
            System.out.println("postId error!");
        }

    }

    @Override
    public void deletePost(List<UrlPram> params) {

        if (postsList.isEmpty()) {
            System.out.println("삭제할 포스트가 존재하지 않습니다.");
            return;
        }

        String postId = getParamValue(params, "postId"); // 이전의 scanner 대체

        if(postId == null) {
            System.out.println("PostId가 제공되어야 합니다.");
            return;
        }

        try {
            int pId = Integer.parseInt(postId); // postId는 String 타입으로 추출했으므로 정수값으로 변환해줌
            if (pId > 0 && pId <= postsList.size()) {
                postsList.remove(pId - 1);
                System.out.println(pId + "번 포스트가 삭제되었습니다.");
            } else {
                System.out.println("잘못된 포스트 번호입니다.");
            }
        } catch ( NumberFormatException e ) {
            System.out.println("postId는 음수일 수 없습니다.");
        }

    }

    @Override
    public void updatePost(List<UrlPram> params) {

        if (postsList.isEmpty()) {
            System.out.println("수정할 포스트가 존재하지 않습니다.");
            return;
        }

        String postId = getParamValue(params, "postId"); // 이전의 scanner 대체

        if(postId == null) {
            System.out.println("PostId가 제공되어야 합니다.");
            return;
        }

        try {
            int pId = Integer.parseInt(postId);
            if (pId > 0 && pId <= postsList.size()) {

                Posts post = postsList.get(pId - 1);

                System.out.print("새로운 제목을 입력하세요: ");
                String newTitle = scanner.nextLine();
                System.out.print("새로운 내용을 입력하세요: ");
                String newText = scanner.nextLine();

                post.setTitle(newTitle);
                post.setText(newText);

                System.out.println("포스트가 수정되었습니다.");
            } else {
                System.out.println("잘못된 포스트 번호입니다.");
            }
        } catch ( NumberFormatException e ) {
            System.out.println("postId는 음수일 수 없습니다.");
        }

    }

    @Override
    public void listPosts() {

        if (postsList.isEmpty()) {
            System.out.println("포스트가 존재하지 않습니다.");
            return;
        }
         System.out.println(postsList.size() + "개의 포스트가 있습니다.");

         for (int i = 0; i < postsList.size(); i++) {

             Posts post = postsList.get(i);

             System.out.println((i+1) + "번 포스트");
             System.out.println("제목 : " + post.getTitle());
             System.out.println("내용 : " + post.getText());

        }
    }

}

