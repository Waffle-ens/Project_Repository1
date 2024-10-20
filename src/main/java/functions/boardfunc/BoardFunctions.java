package functions.boardfunc;

import functions.UrlNotFoundException;
import functions.UrlPram;
import functions.postfunc.Posts;

import java.util.*;

public class BoardFunctions implements BoardFunctionsInterface {

    private List<Boards> boardList;
    private Scanner scanner = new Scanner(System.in);

    public BoardFunctions() { // 생성자
        boardList = new ArrayList<>();
    }


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

        } catch ( UrlNotFoundException e ) {
            System.out.println("오류! 정확한 URL을 입력해주세요.");
        }

    }


    @Override
    public void createBoard() {

        System.out.print("추가할 게시판 이름을 입력하세요: ");
        String boardName = scanner.nextLine();
        boardList.add(new Boards(boardName));

        System.out.println("게시판 '" + boardName + "' (을)를 추가했습니다.");

    }

    private String getParamValue(List<UrlPram> params, String key) {

        for (UrlPram param : params) {

            if (param.getKey().equals(key)) {
                return param.getValue();
            }
        }
        return null;
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

        if (boardName == null) { // 조회할 이름이 제공되지 않는경우
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
        if (posts == null) {

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
