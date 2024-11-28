package boj.bfs;
import java.util.*;

/**
 * 문제
 * N×N크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다. 각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다. 인접한 나라 사이에는 국경선이 존재한다. 모든 나라는 1×1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.
 *
 * 오늘부터 인구 이동이 시작되는 날이다.
 *
 * 인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
 *
 * 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
 * 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
 * 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
 * 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
 * 연합을 해체하고, 모든 국경선을 닫는다.
 * 각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
 *
 * 둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)
 *
 * 인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.
 *
 * 출력
 * 인구 이동이 며칠 동안 발생하는지 첫째 줄에 출력한다.
 *
 * 예제 입력 1
 * 2 20 50
 * 50 30
 * 20 40
 * 예제 출력 1
 * 1
 */
public class boj_16234 {
    static int N, L, R;
    static int[][] population;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 처리
        N = scanner.nextInt();
        L = scanner.nextInt();
        R = scanner.nextInt();
        population = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                population[i][j] = scanner.nextInt();
            }
        }

        int days = 0;

        while (true) {
            visited = new boolean[N][N];
            boolean isPopulationMoved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        // BFS로 연합 찾기
                        List<int[]> union = findUnion(i, j);
                        if (union.size() > 1) {
                            redistributePopulation(union);
                            isPopulationMoved = true;
                        }
                    }
                }
            }

            if (!isPopulationMoved) {
                break;
            }
            days++;
        }

        System.out.println(days);
    }

    // BFS로 연합 찾기
    public static List<int[]> findUnion(int x, int y) {
        List<int[]> union = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        union.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(population[cx][cy] - population[nx][ny]);
                    if (diff >= L && diff <= R) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                        union.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return union;
    }

    // 연합의 인구 재분배
    public static void redistributePopulation(List<int[]> union) {
        int totalPopulation = 0;
        for (int[] cell : union) {
            totalPopulation += population[cell[0]][cell[1]];
        }

        int newPopulation = totalPopulation / union.size();

        for (int[] cell : union) {
            population[cell[0]][cell[1]] = newPopulation;
        }
    }
}
