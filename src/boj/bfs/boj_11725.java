package boj.bfs;
import java.util.*;

/**
 * 문제
 * 루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.
 *
 * 출력
 * 첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.
 *
 * 예제 입력 1
 * 7
 * 1 6
 * 6 3
 * 3 5
 * 4 1
 * 2 4
 * 4 7
 * 예제 출력 1
 * 4
 * 6
 * 1
 * 3
 * 1
 * 4
 * 예제 입력 2
 * 12
 * 1 2
 * 1 3
 * 2 4
 * 3 5
 * 3 6
 * 4 7
 * 4 8
 * 5 9
 * 5 10
 * 6 11
 * 6 12
 * 예제 출력 2
 * 1
 * 1
 * 2
 * 3
 * 3
 * 4
 * 4
 * 5
 * 5
 * 6
 * 6
 */
public class boj_11725 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 그래프 생성
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력받기
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 부모 노드를 저장할 배열
        int[] parent = new int[n + 1];

        // BFS로 부모 노드 찾기
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);  // 루트 노드는 1번
        parent[1] = -1;  // 루트 노드의 부모는 없으므로 -1로 표시

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph.get(current)) {
                if (parent[neighbor] == 0) {  // 방문하지 않은 노드라면
                    parent[neighbor] = current;  // 현재 노드를 부모로 설정
                    queue.add(neighbor);
                }// 큐에 인접한 애들을 넣어야 bfs 가능
            }
        }

        // 부모 노드 출력 (2번 노드부터 N번 노드까지)
        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }

        sc.close();
    }
}
