package boj.dfs;
import java.util.*;

/**
 * 문제
 * 5×5 크기의 숫자판이 있다. 각각의 칸에는 숫자(digit, 0부터 9까지)가 적혀 있다. 이 숫자판의 임의의 위치에서 시작해서, 인접해 있는 네 방향으로 다섯 번 이동하면서, 각 칸에 적혀있는 숫자를 차례로 붙이면 6자리의 수가 된다. 이동을 할 때에는 한 번 거쳤던 칸을 다시 거쳐도 되며, 0으로 시작하는 000123과 같은 수로 만들 수 있다.
 *
 * 숫자판이 주어졌을 때, 만들 수 있는 서로 다른 여섯 자리의 수들의 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 다섯 개의 줄에 다섯 개의 정수로 숫자판이 주어진다.
 *
 * 출력
 * 첫째 줄에 만들 수 있는 수들의 개수를 출력한다.
 *
 * 예제 입력 1
 * 1 1 1 1 1
 * 1 1 1 1 1
 * 1 1 1 1 1
 * 1 1 1 2 1
 * 1 1 1 1 1
 * 예제 출력 1
 * 15
 */
public class boj_2210 {
    static int[][] board = new int[5][5]; // 5x5 숫자판
    static Set<String> uniqueNumbers = new HashSet<>(); // 중복 방지를 위한 Set
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 숫자판 입력 받기
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        sc.close();

        // 모든 위치에서 DFS 시작
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(i, j, "", 0);
            }
        }

        // 고유한 6자리 숫자의 개수 출력
        System.out.println(uniqueNumbers.size());
    }

    // DFS로 6자리 숫자 만들기
    public static void dfs(int x, int y, String num, int depth) {
        if (depth == 6) { // 6자리 숫자가 만들어지면 Set에 저장
            uniqueNumbers.add(num);
            return;
        }

        // 상, 하, 좌, 우로 이동
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                dfs(nx, ny, num + board[nx][ny], depth + 1);
            }
        }
    }
}
