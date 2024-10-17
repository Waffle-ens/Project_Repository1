package functions;

import java.util.*;

public interface BoardFunctions {
    void createPost(Scanner scanner);//작성
    void readPost(Scanner scanner);//조회
    void deletePost(Scanner scanner);//삭제
    void updatePost(Scanner scanner);//업데이트
    //1-7 요구조건에서 목록이 추가됨
    void listPosts();
}
