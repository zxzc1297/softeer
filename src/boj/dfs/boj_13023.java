package boj.dfs;
import java.util.*;
/**
 * 문제
 * BOJ 알고리즘 캠프에는 총 N명이 참가하고 있다. 사람들은 0번부터 N-1번으로 번호가 매겨져 있고, 일부 사람들은 친구이다.
 *
 * 오늘은 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
 *
 * A는 B와 친구다.
 * B는 C와 친구다.
 * C는 D와 친구다.
 * D는 E와 친구다.
 * 위와 같은 친구 관계가 존재하는지 안하는지 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 사람의 수 N (5 ≤ N ≤ 2000)과 친구 관계의 수 M (1 ≤ M ≤ 2000)이 주어진다.
 *
 * 둘째 줄부터 M개의 줄에는 정수 a와 b가 주어지며, a와 b가 친구라는 뜻이다. (0 ≤ a, b ≤ N-1, a ≠ b) 같은 친구 관계가 두 번 이상 주어지는 경우는 없다.
 *
 * 출력
 * 문제의 조건에 맞는 A, B, C, D, E가 존재하면 1을 없으면 0을 출력한다.
 *
 * 예제 입력 1
 * 5 4
 * 0 1
 * 1 2
 * 2 3
 * 3 4
 * 예제 출력 1
 * 1
 */
public class boj_13023 {
    static int n, m; // 사람 수와 친구 관계 수
    static List<Integer>[] graph; // 친구 관계를 저장할 인접 리스트
    static boolean[] visited; // 방문 여부 확인
    static boolean found; // 조건을 만족하는 관계를 찾았는지 여부

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        m = scanner.nextInt();

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            graph[a].add(b);
            graph[b].add(a); // 양방향 그래프
        }

        visited = new boolean[n];
        found = false;

        for (int i = 0; i < n; i++) {
            dfs(i, 0); // 깊이 0부터 시작
            if (found) break; // 조건을 만족하면 탐색 종료
        }

        System.out.println(found ? 1 : 0);
    }

    static void dfs(int node, int depth) {
        if (found) return; // 이미 조건을 만족한 경우 탐색 종료

        if (depth == 4) { // 깊이 4에 도달하면 조건 만족
            found = true;
            return;
        }

        visited[node] = true; // 현재 노드 방문 처리

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) { // 방문하지 않은 인접 노드 탐색
                dfs(neighbor, depth + 1);
            }
        }

        visited[node] = false; // 백트래킹: 다른 경로를 탐색하기 위해 방문 해제
    }
}
