package functions;

import java.util.*;

public class Functions implements BoardFunctions {

    private List<Posts> postsList;

    public Functions() {
        postsList = new ArrayList<>();
    }

    @Override
    public void createPost(Scanner scanner) {

        System.out.print("제목 : ");
        String title = scanner.nextLine();
        System.out.print("내용 : ");
        String text = scanner.nextLine();

        Posts post = new Posts(title, text);
        postsList.add(post);
        System.out.println("포스팅 완료");

    }

    @Override
    public void readPost(Scanner scanner) {

        if (postsList.isEmpty()) {
            throw new PostsNotFoundException("포스트가 존재하지 않습니다.");
        } else {

            listPosts();

            System.out.println("조회하고싶은 포스트 번호를 입력하세요.");
            int postNum = scanner.nextInt();
            scanner.nextLine();

            if (postNum > 0 && postNum <= postsList.size()) {
                Posts selectedPost = postsList.get(postNum - 1);
                System.out.println(postNum + "번 포스트");
                System.out.println("제목 : " + selectedPost.getTitle());
                System.out.println("내용 : " + selectedPost.getText());
            }

        }
    }

    @Override
    public void deletePost(Scanner scanner) {

        if (postsList.isEmpty()) {
            throw new PostsNotFoundException("삭제할 포스트가 존재하지 않습니다.");
        } else {

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
    }

    @Override
    public void updatePost(Scanner scanner) {

        if (postsList.isEmpty()) {
            throw new PostsNotFoundException("수정할 포스트가 존재하지 않습니다.");
        } else {

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

