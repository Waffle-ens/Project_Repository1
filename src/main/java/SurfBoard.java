import functions.boardfunc.BoardFunctions;
import functions.postfunc.PostFunctions;
import functions.UrlNotFoundException;

import java.util.*;

public class SurfBoard {

    public static void main(String[] args) {

        PostFunctions postF = new PostFunctions();
        BoardFunctions boardF = new BoardFunctions();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("손님> ");
            String url = sc.nextLine().trim();

            if (url.equals("/종료") || url.equals("/exit")) {
                System.out.println("프로그램을 종료합니다,");
                break; // 종료 기능
            }

            try {
                if (url.startsWith("/posts")) {
                    // 게시글 관련 URL 처리 (PostFunctions)
                    postF.parsingUrl(url);
                } else if (url.startsWith("/boards")) {
                    // 게시판 관련 URL 처리 (BoardFunctions)
                    boardF.parsingUrl(url);
                } else {
                    System.out.println("유효하지 않은 URL입니다. /posts 또는 /boards로 시작하는 URL을 입력해주세요.");
                }

            } catch (UrlNotFoundException e) {
                System.out.println("URL을 정확히 입력해주세요.");
            }

        }

    }

}
