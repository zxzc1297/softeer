package boj.dfs;
import java.util.*;

/**
 * 문제
 * 그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.
 *
 * 그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.
 *
 * 입력
 * 입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K가 주어진다. 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다. 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다. 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데, 각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다.
 *
 * 출력
 * K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.
 *
 * 제한
 * 2 ≤ K ≤ 5
 * 1 ≤ V ≤ 20,000
 * 1 ≤ E ≤ 200,000
 * 예제 입력 1
 * 2
 * 3 2
 * 1 3
 * 2 3
 * 4 4
 * 1 2
 * 2 3
 * 3 4
 * 4 2
 * 예제 출력 1
 * YES
 * NO
 */
public class boj_1707 {
    static int[] color; // 각 정점의 색을 저장하는 배열 (0: 미방문, 1: 빨간색, 2: 파란색)
    static ArrayList<Integer>[] graph; // 인접 리스트로 그래프를 저장
    static boolean isRight; // 이분 그래프 여부

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt(); // 테스트 케이스 개수

        for (int t = 0; t < K; t++) {
            int V = sc.nextInt(); // 정점의 수
            int E = sc.nextInt(); // 간선의 수

            // 그래프 초기화
            graph = new ArrayList[V + 1];
            color = new int[V + 1]; // 색을 저장하는 배열
            isRight = true; // 이분 그래프 여부 초기화

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            // 간선 정보 입력
            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                graph[u].add(v);
                graph[v].add(u);
            }

            // 각 정점을 DFS로 방문하면서 이분 그래프 여부 확인
            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) { // 방문하지 않은 정점이면
                    dfs(i, 1); // 색 1로 시작
                }
            }

            // 결과 출력
            System.out.println(isRight ? "YES" : "NO");
        }
        sc.close();
    }

    static void dfs(int node, int c) {
        color[node] = c; // 현재 노드에 색칠 (1 또는 2)

        // 인접한 노드 탐색
        for (int next : graph[node]) {
            if (color[next] == 0) {
                // 인접 노드가 아직 색칠되지 않았다면, 다른 색으로 칠함
                dfs(next, 3 - c); // 1 -> 2, 2 -> 1로 색을 변경
            } else if (color[next] == color[node]) {
                // 인접한 노드가 같은 색으로 칠해져 있다면 이분 그래프가 아님
                isRight = false;
            }
        }
    }
}
