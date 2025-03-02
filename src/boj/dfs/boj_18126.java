package boj.dfs;
import java.util.*;

/**
 * 문제
 * 텔레토비 동산에 사는 너구리 구구는 입구, 거실, 주방, 안방, 공부방, 운동실, 음악실, 음식 창고 등 N개의 방을 가지고 있다. 입구를 포함한 모든 방은 1부터 N까지의 번호가 있고, 입구는 1번이다.  구구의 집으로 들어가는 입구는 한 개이며 입구과 모든 방들은 총 N-1개의 길로 서로 오고 갈 수 있다.
 *
 * 구구는 스머프 동산에서 멜론아 아이스크림을 발견했다. 구구는 무더운 여름 햇살을 피해 최대한 입구에서 먼 방에 아이스크림을 숨기려고 한다.
 *
 * 구구가 집 입구에서 멜론아 아이스크림을 숨기려고 하는 방까지 이동하는 거리를 구하여라.
 *
 * 입력
 * 첫째 줄에 정수 N(1 ≤ N ≤ 5,000)이 주어진다.
 *
 * 다음 N-1개의 줄에 구구의 집의 모든 길의 정보가 정수 A, B, C(1 ≤ A, B ≤ N, 1 ≤ C ≤ 1,000,000,000)로 주어진다.
 *
 * A번 방과 B번 방 사이를 양방향으로 연결하는 길의 길이가 C임을 의미한다.
 *
 * 출력
 * 구구가 집 입구에서 멜론아 아이스크림을 숨기려고 하는 방까지 이동하는 거리를 구하여라.
 *
 * 예제 입력 1
 * 4
 * 1 2 3
 * 2 3 2
 * 2 4 4
 * 예제 출력 1
 * 7
 */
public class boj_18126 {
    static class Edge {
        int to, cost;
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int N;
    static List<Edge>[] graph;
    static boolean[] visited;
    static long maxDistance = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        if (N == 1) {  // 방이 1개뿐이면 최대 거리는 0
            System.out.println(0);
            return;
        }

        initializeGraph(sc); // 그래프 입력 처리
        visited[1] = true;
        dfs(1, 0); // DFS 실행
        System.out.println(maxDistance);
    }

    static void initializeGraph(Scanner sc) {
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            graph[A].add(new Edge(B, C));
            graph[B].add(new Edge(A, C));
        }
    }

    static void dfs(int node, long dist) {
        if (dist > maxDistance) {
            maxDistance = dist;
        }

        for (Edge edge : graph[node]) {
            if (!visited[edge.to]) {
                visited[edge.to] = true;
                dfs(edge.to, dist + edge.cost);
            }
        }
    }
}
