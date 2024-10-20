package functions.boardfunc;

import functions.CommonFunctions;
import functions.UrlNotFoundException;
import functions.UrlPram;
import functions.postfunc.Posts;

import java.util.*;

public class BoardFunctions extends CommonFunctions implements BoardFunctionsInterface {

    private List<Boards> boardList;
    private Scanner scanner = new Scanner(System.in);

    public BoardFunctions() { // 생성자
        boardList = new ArrayList<>();
    }


    public void parsingUrl(String url) throws UrlNotFoundException {


            //기존의 중복코드 CommonFunctions 에 재정의
            ParsedUrl parsedUrl = CommonFunctions.parseUrl(url);
            String actions = parsedUrl.getAction();
            List<UrlPram> params = parsedUrl.getParams();

            switch (actions) { // 파싱한 url 파라미터 기능 구현부
                case "create":
                    createBoard();
                    break;
                case "remove":
                    removeBoard(params);
                    break;
                case "edit":
                    editBoard(params);
                    break;
                case "view":
                    viewBoard(params);
                    break;
                default: // 잘못된 url 기능 예외처리
                    throw new UrlNotFoundException(actions + " (은)는 유효한 기능이 아닙니다.");
            }

    }


    @Override
    public void createBoard() {

        System.out.print("추가할 게시판 이름을 입력하세요: ");
        String boardName = scanner.nextLine();
        boardList.add(new Boards(boardName));

        System.out.println("게시판 '" + boardName + "' (을)를 추가했습니다.");

    }


    public Boards getBoard(List<UrlPram> params) { // 각 메서드에 사용할 boardId를 추출해서 반환하도록 설정

        String boardId = getParamValue(params, "boardId");

        if (boardId == null) {
            System.out.println("boardId가 필요합니다.");
            return null;
        }

        int bId = Integer.parseInt(boardId); // boardId를 정수형으로 변환

        if ( bId > 0 && bId <= boardList.size() ) {
            return boardList.get(bId - 1);
        } else {
            System.out.println("boardId error!");
            return null;
        }

    }

    @Override
    public void removeBoard(List<UrlPram> params) {

        Boards board = getBoard(params);

        if (board != null) {
            boardList.remove(board);
            System.out.println("게시판이 삭제되었습니다.");
        } else {
            return;
        }

    }

    @Override
    public void editBoard(List<UrlPram> params) {

        Boards board = getBoard(params);

        if (board != null) {
            System.out.println("게시판 이름을 입력해주세요.");

            String newBoardName = scanner.nextLine();
            board.setName(newBoardName);

            System.out.println("게시판 이름이 수정되었습니다.");
        } else {
            return;
        }
    }

    @Override
    public void viewBoard(List<UrlPram> params) {

        String boardName = getParamValue(params, "boardName");

        if (boardName.isEmpty()) { // 조회할 이름이 제공되지 않는경우
            System.out.println("조회할 게시판 이름이 있어야합니다.");
            return;
        }

        Boards searchBoard = null;
        for (Boards board : boardList) {
            if (board.getName().equals(boardName)) {
                searchBoard = board;
                break;
            }
        }

        if(searchBoard == null){
            System.out.println("그런 게시판은 없는데요..?");
            return;
        }

        List<Posts> posts = searchBoard.getPosts();
        if (posts.isEmpty()) {

            System.out.println("게시판이 비어있는데요..?");

        } else {
            System.out.println("글 번호 / 글 제목 / 작성일");
            for (int i = 0; i < posts.size(); i++) {
                Posts post = posts.get(i);
                System.out.println((i +1)+" / "+post.getTitle()+" / "+ new Date());
            }

        }

    }

}
