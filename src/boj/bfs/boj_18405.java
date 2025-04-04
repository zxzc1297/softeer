package boj.bfs;
import java.util.*;

/**
 * 문제
 * NxN 크기의 시험관이 있다. 시험관은 1x1 크기의 칸으로 나누어지며, 특정한 위치에는 바이러스가 존재할 수 있다. 모든 바이러스는 1번부터 K번까지의 바이러스 종류 중 하나에 속한다.
 *
 * 시험관에 존재하는 모든 바이러스는 1초마다 상, 하, 좌, 우의 방향으로 증식해 나간다. 단, 매 초마다 번호가 낮은 종류의 바이러스부터 먼저 증식한다. 또한 증식 과정에서 특정한 칸에 이미 어떠한 바이러스가 존재한다면, 그 곳에는 다른 바이러스가 들어갈 수 없다.
 *
 * 시험관의 크기와 바이러스의 위치 정보가 주어졌을 때, S초가 지난 후에 (X,Y)에 존재하는 바이러스의 종류를 출력하는 프로그램을 작성하시오. 만약 S초가 지난 후에 해당 위치에 바이러스가 존재하지 않는다면, 0을 출력한다. 이 때 X와 Y는 각각 행과 열의 위치를 의미하며, 시험관의 가장 왼쪽 위에 해당하는 곳은 (1,1)에 해당한다.
 *
 * 예를 들어 다음과 같이 3x3 크기의 시험관이 있다고 하자. 서로 다른 1번, 2번, 3번 바이러스가 각각 (1,1), (1,3), (3,1)에 위치해 있다. 이 때 2초가 지난 뒤에 (3,2)에 존재하는 바이러스의 종류를 계산해보자.
 *
 *
 *
 * 1초가 지난 후에 시험관의 상태는 다음과 같다.
 *
 *
 *
 * 2초가 지난 후에 시험관의 상태는 다음과 같다.
 *
 *
 *
 * 결과적으로 2초가 지난 뒤에 (3,2)에 존재하는 바이러스의 종류는 3번 바이러스다. 따라서 3을 출력하면 정답이다.
 *
 * 입력
 * 첫째 줄에 자연수 N, K가 공백을 기준으로 구분되어 주어진다. (1 ≤ N ≤ 200, 1 ≤ K ≤ 1,000) 둘째 줄부터 N개의 줄에 걸쳐서 시험관의 정보가 주어진다. 각 행은 N개의 원소로 구성되며, 해당 위치에 존재하는 바이러스의 번호가 공백을 기준으로 구분되어 주어진다. 단, 해당 위치에 바이러스가 존재하지 않는 경우 0이 주어진다. 또한 모든 바이러스의 번호는 K이하의 자연수로만 주어진다. N+2번째 줄에는 S, X, Y가 공백을 기준으로 구분되어 주어진다. (0 ≤ S ≤ 10,000, 1 ≤ X, Y ≤ N)
 *
 * 출력
 * S초 뒤에 (X,Y)에 존재하는 바이러스의 종류를 출력한다. 만약 S초 뒤에 해당 위치에 바이러스가 존재하지 않는다면, 0을 출력한다.
 *
 * 예제 입력 1
 * 3 3
 * 1 0 2
 * 0 0 0
 * 3 0 0
 * 2 3 2
 * 예제 출력 1
 * 3
 * 예제 입력 2
 * 3 3
 * 1 0 2
 * 0 0 0
 * 3 0 0
 * 1 2 2
 * 예제 출력 2
 * 0
 */
public class boj_18405 {
    static int N, K, S, X, Y;
    static int[][] lab;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static class Virus implements Comparable<Virus> {
        int x, y, type;

        public Virus(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public int compareTo(Virus o) {
            return this.type - o.type; // 바이러스 번호 작은 순 정렬
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        lab = new int[N][N];

        List<Virus> virusList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                lab[i][j] = sc.nextInt();
                if (lab[i][j] > 0) {
                    virusList.add(new Virus(i, j, lab[i][j]));
                }
            }
        }

        // 바이러스 번호 기준 오름차순 정렬 후 큐에 삽입
        Collections.sort(virusList);
        Queue<Virus> queue = new LinkedList<>(virusList);

        S = sc.nextInt();
        X = sc.nextInt() - 1;
        Y = sc.nextInt() - 1;

        bfs(queue);
        System.out.println(lab[X][Y]);
    }

    static void bfs(Queue<Virus> queue) {
        int time = 0;

        while (!queue.isEmpty()) {
            if (time == S) return; // S초 후 종료

            int size = queue.size(); // 현재 초에서 확산할 바이러스 개수
            for (int i = 0; i < size; i++) {
                Virus v = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = v.x + dx[d];
                    int ny = v.y + dy[d];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && lab[nx][ny] == 0) {
                        lab[nx][ny] = v.type;
                        queue.add(new Virus(nx, ny, v.type)); // 다음 초에 확산될 바이러스 저장
                    }
                }
            }
            time++; // 1초 경과
        }
    }
}
