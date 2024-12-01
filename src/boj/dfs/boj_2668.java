package boj.dfs;
import java.util.*;

/**
 * 문제
 * 세로 두 줄, 가로로 N개의 칸으로 이루어진 표가 있다. 첫째 줄의 각 칸에는 정수 1, 2, …, N이 차례대로 들어 있고 둘째 줄의 각 칸에는 1이상 N이하인 정수가 들어 있다. 첫째 줄에서 숫자를 적절히 뽑으면, 그 뽑힌 정수들이 이루는 집합과, 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다. 이러한 조건을 만족시키도록 정수들을 뽑되, 최대로 많이 뽑는 방법을 찾는 프로그램을 작성하시오. 예를 들어, N=7인 경우 아래와 같이 표가 주어졌다고 하자.
 *
 *
 *
 * 이 경우에는 첫째 줄에서 1, 3, 5를 뽑는 것이 답이다. 첫째 줄의 1, 3, 5밑에는 각각 3, 1, 5가 있으며 두 집합은 일치한다. 이때 집합의 크기는 3이다. 만약 첫째 줄에서 1과 3을 뽑으면, 이들 바로 밑에는 정수 3과 1이 있으므로 두 집합이 일치한다. 그러나, 이 경우에 뽑힌 정수의 개수는 최대가 아니므로 답이 될 수 없다.
 *
 * 입력
 * 첫째 줄에는 N(1≤N≤100)을 나타내는 정수 하나가 주어진다. 그 다음 줄부터는 표의 둘째 줄에 들어가는 정수들이 순서대로 한 줄에 하나씩 입력된다.
 *
 * 출력
 * 첫째 줄에 뽑힌 정수들의 개수를 출력하고, 그 다음 줄부터는 뽑힌 정수들을 작은 수부터 큰 수의 순서로 한 줄에 하나씩 출력한다.
 *
 * 예제 입력 1
 * 7
 * 3
 * 1
 * 1
 * 5
 * 5
 * 4
 * 6
 * 예제 출력 1
 * 3
 * 1
 * 3
 * 5
 */
public class boj_2668 {
    static int[] table;       // 둘째 줄의 숫자를 저장하는 배열
    static boolean[] visited; // 방문 여부를 저장하는 배열
    static boolean[] inCycle; // 현재 사이클에 포함 여부를 저장하는 배열
    static List<Integer> result; // 결과 저장용 리스트

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 숫자의 개수 N
        table = new int[n + 1];
        visited = new boolean[n + 1];
        inCycle = new boolean[n + 1];
        result = new ArrayList<>();

        // 둘째 줄 입력
        for (int i = 1; i <= n; i++) {
            table[i] = scanner.nextInt();
        }

        // 각 노드에서 DFS 실행
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i, new ArrayList<>());
            }
        }

        // 결과 정렬 및 출력
        Collections.sort(result);
        System.out.println(result.size());
        for (int num : result) {
            System.out.println(num);
        }
    }

    public static void dfs(int start, List<Integer> path) {
        if (visited[start]) { // 이미 방문한 노드라면
            if (path.contains(start)) { // 현재 경로에 포함된 경우, 사이클 발견
                int cycleStartIndex = path.indexOf(start);
                for (int i = cycleStartIndex; i < path.size(); i++) {
                    int num = path.get(i);
                    inCycle[num] = true; // 사이클에 포함 표시
                    result.add(num);
                }
            }
            return;
        }

        visited[start] = true;
        path.add(start);
        dfs(table[start], path);
        path.remove(path.size() - 1); // 경로에서 제거
    }
}
