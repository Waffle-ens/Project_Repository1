package functions.boardfunc;

import functions.UrlPram;

import java.util.*;

public interface BoardFunctionsInterface {
    void createBoard();
    void removeBoard(List<UrlPram> params);
    void editBoard(List<UrlPram> params);
    void viewBoard(List<UrlPram> params);
}
