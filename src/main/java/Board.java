import functions.Functions;

import java.util.*;

public class Board {

    public static void main(String[] args) {

        Functions boardF = new Functions();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("명령어> ");
            String order = sc.nextLine().trim();

            if (order.equals("종료") || order.equals("exit")) {
                System.out.println("프로그램을 종료합니다,");
                break; // 종료 기능
            }
            if (order.equals("작성")) {
                boardF.createPost(sc);
            } else if (order.equals("조회")) {
                boardF.readPost(sc);
            } else if (order.equals("삭제")) {
                boardF.deletePost(sc);
            } else if (order.equals("수정")) {
                boardF.updatePost(sc);
            } else if (order.equals("목록")) {
                boardF.listPosts();
            }
            else {
                System.out.println("존재하지 않는 명령어 입니다.");
            }

        }

    }

}
