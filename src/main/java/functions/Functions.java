package functions;

import java.util.*;

public class Functions implements PostFunctions {

    private List<Posts> postsList;
    Scanner scanner = new Scanner(System.in); //url 기능을 추가면서 scanner 객체가 필요해짐

    public Functions() { // 생성자
        postsList = new ArrayList<>();
    }

    @Override
    public void parsingUrl(String url) throws UrlNotFoundException {
        try {

            String[] blocks = url.split("\\?",2); // 파라미터 분리
            String[] path = blocks[0].split("/"); // 경로 분리

            if ( path.length < 3 ){ // 정상적인 URL인지 검사

                throw new UrlNotFoundException("URL이 올바르지 않습니다.");

            }

            String actions = path[2];


            List<UrlPram> params = new ArrayList<>();

            if (blocks.length > 1) {

                String[] paramSet = blocks[1].split("&");

                for (String set : paramSet) {

                    String[] keyNum = set.split("=");

                    if (keyNum.length == 2) {

                        String key = keyNum[0];
                        String value = keyNum[1];

                        UrlPram isExist = null;

                        for (UrlPram param : params) {
                            if (param.getKey().equals(key)) {
                                isExist = param;
                                break;
                            }
                        }
                        if (isExist != null) {
                            isExist.setValue(value);
                        } else {
                            params.add(new UrlPram(key, value));
                        }

                    }
                }
            }

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

        } catch ( UrlNotFoundException e ) {
            System.out.println("오류! 정확한 URL을 입력해주세요.");
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

        listPosts();

        System.out.println("조회하고싶은 포스트 번호를 입력하세요.");
        int postNum = scanner.nextInt();
        scanner.nextLine();

        if (postNum > 0 && postNum <= postsList.size()) {
             Posts selectedPost = postsList.get(postNum - 1);
             System.out.println(postNum + "번 포스트");
             System.out.println("제목 : " + selectedPost.getTitle());
             System.out.println("내용 : " + selectedPost.getText());
        } else {
            System.out.println("잘못된 포스트 번호 입니다.");
        }

    }

    @Override
    public void deletePost(List<UrlPram> params) {

        if (postsList.isEmpty()) {
            System.out.println("삭제할 포스트가 존재하지 않습니다.");
            return;
        }

        listPosts();

        System.out.println("삭제하고싶은 포스트 번호를 입력하세요.");
        int postNum = scanner.nextInt();
        scanner.nextLine();

        if (postNum > 0 && postNum <= postsList.size()) {
            postsList.remove(postNum - 1);
            System.out.println(postNum+"번 포스트가 삭제되었습니다.");
        } else {
            System.out.println("잘못된 포스트 번호입니다.");
        }

    }

    @Override
    public void updatePost(List<UrlPram> params) {

        if (postsList.isEmpty()) {
            System.out.println("수정할 포스트가 존재하지 않습니다.");
        }

        listPosts();

        System.out.println("수정할 포스트 번호를 입력하세요.");
        int postNum = scanner.nextInt();
        scanner.nextLine();

        if (postNum > 0 && postNum <= postsList.size()) {

            Posts post = postsList.get(postNum - 1);

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

    }

    @Override
    public void listPosts() {

        if (postsList.isEmpty()) {
            throw new PostsNotFoundException("포스트가 존재하지 않습니다.");
        } else {
            System.out.println(postsList.size() + "개의 포스트가 있습니다.");

            for (int i = 0; i < postsList.size(); i++) {

                Posts post = postsList.get(i);

                System.out.println((i+1) + "번 포스트");
                System.out.println("제목 : " + post.getTitle());
                System.out.println("내용 : " + post.getText());

            }
        }
    }

}

