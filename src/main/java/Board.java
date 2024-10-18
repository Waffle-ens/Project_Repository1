import functions.Functions;
import functions.UrlNotFoundException;

import java.util.*;

public class Board {

    public static void main(String[] args) {

        Functions boardF = new Functions();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("손님> ");
            String url = sc.nextLine().trim();

            if (url.equals("/종료") || url.equals("/exit")) {
                System.out.println("프로그램을 종료합니다,");
                break; // 종료 기능
            }

            try {
                boardF.parsingUrl(url);
            } catch (UrlNotFoundException e) {
                System.out.println("오류 발생! URL을 정확히 입력해주세요.");
            }

        }

    }

}
