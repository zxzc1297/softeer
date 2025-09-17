package programmers;
import java.util.*;
public class q_12646 {
    
    public static void main(String[] args){
        System.out.println(Arrays.toString(solution(15)));
    }

    public static int[][] solution(int n) {
        List<int[]> moves = new ArrayList<>();
        hanoi(n, 1, 3, 2, moves);

        // List<int[]> → int[][] 변환
        int[][] result = new int[moves.size()][2];
        for (int i = 0; i < moves.size(); i++) {
            result[i] = moves.get(i);
        }
        return result;
    }

    // 하노이 재귀
    private static void hanoi(int n, int from, int to, int via, List<int[]> moves) {
        if (n == 0) return;
        // 1. n-1개를 보조 기둥으로 옮기기
        hanoi(n - 1, from, via, to, moves);
        // 2. 가장 큰 원판을 목적 기둥으로 옮기기
        moves.add(new int[]{from, to});
        // 3. n-1개를 보조 기둥에서 목적 기둥으로 옮기기
        hanoi(n - 1, via, to, from, moves);
    }
}
