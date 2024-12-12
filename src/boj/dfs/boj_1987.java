package boj.dfs;
import java.util.*;

/**
 * 문제
 * 세로
 * $R$칸, 가로
 * $C$칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (
 * $1$행
 * $1$열) 에는 말이 놓여 있다.
 *
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 *
 * 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
 *
 * 입력
 * 첫째 줄에
 * $R$과
 * $C$가 빈칸을 사이에 두고 주어진다. (
 * $1 ≤ R,C ≤ 20$) 둘째 줄부터
 * $R$개의 줄에 걸쳐서 보드에 적혀 있는
 * $C$개의 대문자 알파벳들이 빈칸 없이 주어진다.
 *
 * 출력
 * 첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.
 *
 * 예제 입력 1
 * 2 4
 * CAAB
 * ADCB
 * 예제 출력 1
 * 3
 * 예제 입력 2
 * 3 6
 * HFDFFB
 * AJHGDH
 * DGAGEH
 * 예제 출력 2
 * 6
 * 예제 입력 3
 * 5 5
 * IEFCJ
 * FHFKC
 * FFALF
 * HFGCF
 * HMCHH
 * 예제 출력 3
 * 10
 */
public class boj_1987 {
    static int R, C;
    static char[][] board;
    static boolean[] visited;
    static int maxCount = 0;

    // 이동 방향 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        R = scanner.nextInt();
        C = scanner.nextInt();
        scanner.nextLine(); // 줄바꿈 제거

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = scanner.nextLine().toCharArray();
        }

        visited = new boolean[26]; // 알파벳 방문 여부
        dfs(0, 0, 1); // 시작 칸 포함, 초기 이동 수는 1

        System.out.println(maxCount);
    }

    public static void dfs(int x, int y, int count) {
        maxCount = Math.max(maxCount, count);
        visited[board[x][y] - 'A'] = true; // 현재 알파벳 방문 처리

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 보드 경계 내에 있고, 새로운 알파벳일 경우
            if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[board[nx][ny] - 'A']) {
                dfs(nx, ny, count + 1);
            }
        }

        visited[board[x][y] - 'A'] = false; // 백트래킹
    }
}
