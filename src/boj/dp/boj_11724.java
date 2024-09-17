package boj.dp;
import java.util.*;

/**
 * 문제
 * 방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
 *
 * 출력
 * 첫째 줄에 연결 요소의 개수를 출력한다.
 *
 * 예제 입력 1
 * 6 5
 * 1 2
 * 2 5
 * 5 1
 * 3 4
 * 4 6
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 6 8
 * 1 2
 * 2 5
 * 5 1
 * 3 4
 * 4 6
 * 5 4
 * 2 4
 * 2 3
 * 예제 출력 2
 * 1
 */
public class boj_11724 {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 정점의 개수 N, 간선의 개수 M
        int N = sc.nextInt();
        int M = sc.nextInt();

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 입력
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);  // 무방향 그래프이므로 양쪽 다 연결
        }

        // 방문 배열 초기화
        visited = new boolean[N + 1];

        int componentCount = 0;

        // 모든 정점에 대해 탐색 시도
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                // 새로운 연결 요소 발견, DFS 탐색 시작
                dfs(i);
                componentCount++;  // 연결 요소 개수 증가
            }
        }

        // 결과 출력
        System.out.println(componentCount);
        sc.close();
    }

    // 깊이 우선 탐색 (DFS)
    static void dfs(int node) {
        visited[node] = true;

        // 현재 노드에 연결된 모든 이웃 노드 탐색
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}
