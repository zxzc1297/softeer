package boj.dfs;
import java.util.*;

/**
 * 문제
 * 눈금의 간격이 1인 M×N(M,N≤100)크기의 모눈종이가 있다. 이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때, 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.
 *
 * 예를 들어 M=5, N=7 인 모눈종이 위에 <그림 1>과 같이 직사각형 3개를 그렸다면, 그 나머지 영역은 <그림 2>와 같이 3개의 분리된 영역으로 나누어지게 된다.
 *
 *
 *
 * <그림 2>와 같이 분리된 세 영역의 넓이는 각각 1, 7, 13이 된다.
 *
 * M, N과 K 그리고 K개의 직사각형의 좌표가 주어질 때, K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지, 그리고 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 M과 N, 그리고 K가 빈칸을 사이에 두고 차례로 주어진다. M, N, K는 모두 100 이하의 자연수이다. 둘째 줄부터 K개의 줄에는 한 줄에 하나씩 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값이 빈칸을 사이에 두고 차례로 주어진다. 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)이다. 입력되는 K개의 직사각형들이 모눈종이 전체를 채우는 경우는 없다.
 *
 * 출력
 * 첫째 줄에 분리되어 나누어지는 영역의 개수를 출력한다. 둘째 줄에는 각 영역의 넓이를 오름차순으로 정렬하여 빈칸을 사이에 두고 출력한다.
 *
 * 예제 입력 1
 * 5 7 3
 * 0 2 4 4
 * 1 1 2 5
 * 4 0 6 2
 * 예제 출력 1
 * 3
 * 1 7 13
 */
public class boj_2583 {
    static int M, N, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};  // 상, 우, 하, 좌
    static int[] dy = {1, 0, -1, 0};  // 상, 우, 하, 좌

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        K = sc.nextInt();

        map = new int[M][N];  // M x N 크기의 모눈종이
        visited = new boolean[M][N];

        // 직사각형 그리기
        for (int i = 0; i < K; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    map[y][x] = 1;  // 직사각형 내부를 1로 표시
                }
            }
        }

        List<Integer> areas = new ArrayList<>();  // 각 영역의 넓이를 저장할 리스트

        // Flood Fill (DFS)를 사용하여 영역을 탐색
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    int area = dfs(i, j);  // 새로운 영역 발견
                    areas.add(area);  // 영역의 넓이 추가
                }
            }
        }

        // 출력
        Collections.sort(areas);  // 넓이를 오름차순으로 정렬
        System.out.println(areas.size());  // 분리된 영역의 개수 출력
        for (int area : areas) {
            System.out.print(area + " ");
        }
    }

    // DFS를 사용하여 영역의 넓이를 계산
    static int dfs(int y, int x) {
        visited[y][x] = true;
        int area = 1;  // 현재 칸도 포함되므로 넓이는 1부터 시작

        for (int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (ny >= 0 && ny < M && nx >= 0 && nx < N && map[ny][nx] == 0 && !visited[ny][nx]) {
                area += dfs(ny, nx);  // 인접한 칸도 재귀적으로 탐색
            }
        }

        return area;  // 탐색한 영역의 총 넓이를 반환
    }
}
