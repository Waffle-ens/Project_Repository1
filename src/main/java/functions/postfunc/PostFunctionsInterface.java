package functions.postfunc;

import functions.UrlPram;

import java.util.*;

public interface PostFunctionsInterface {
    void createPost(List<UrlPram> params);//작성
    void readPost(List<UrlPram> params);//조회
    void deletePost(List<UrlPram> params);//삭제
    void updatePost(List<UrlPram> params);//업데이트
    //1-7 요구조건에서 목록이 추가됨
    //2-1 요구조건으로 인해 매개변수로 입력받은 url 파라미터를 가져옴
    void listPosts();
    void parsingUrl(String url);
}
