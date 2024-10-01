package boj.dfs;
import java.util.*;

/**
 * 문제
 * 우리 나라는 가족 혹은 친척들 사이의 관계를 촌수라는 단위로 표현하는 독특한 문화를 가지고 있다. 이러한 촌수는 다음과 같은 방식으로 계산된다. 기본적으로 부모와 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산한다. 예를 들면 나와 아버지, 아버지와 할아버지는 각각 1촌으로 나와 할아버지는 2촌이 되고, 아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌이 된다.
 *
 * 여러 사람들에 대한 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산하는 프로그램을 작성하시오.
 *
 * 입력
 * 사람들은 1, 2, 3, …, n (1 ≤ n ≤ 100)의 연속된 번호로 각각 표시된다. 입력 파일의 첫째 줄에는 전체 사람의 수 n이 주어지고, 둘째 줄에는 촌수를 계산해야 하는 서로 다른 두 사람의 번호가 주어진다. 그리고 셋째 줄에는 부모 자식들 간의 관계의 개수 m이 주어진다. 넷째 줄부터는 부모 자식간의 관계를 나타내는 두 번호 x,y가 각 줄에 나온다. 이때 앞에 나오는 번호 x는 뒤에 나오는 정수 y의 부모 번호를 나타낸다.
 *
 * 각 사람의 부모는 최대 한 명만 주어진다.
 *
 * 출력
 * 입력에서 요구한 두 사람의 촌수를 나타내는 정수를 출력한다. 어떤 경우에는 두 사람의 친척 관계가 전혀 없어 촌수를 계산할 수 없을 때가 있다. 이때에는 -1을 출력해야 한다.
 *
 * 예제 입력 1
 * 9
 * 7 3
 * 7
 * 1 2
 * 1 3
 * 2 7
 * 2 8
 * 2 9
 * 4 5
 * 4 6
 * 예제 출력 1
 * 3
 * 예제 입력 2
 * 9
 * 8 6
 * 7
 * 1 2
 * 1 3
 * 2 7
 * 2 8
 * 2 9
 * 4 5
 * 4 6
 * 예제 출력 2
 * -1
 */
public class boj_2644 {
    static int[][] arr;
    static int[] visited;
    static int answer,n,m;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n+1][n+1];
        visited = new int[n+1];

        //촌수 관계 표현 이어져있으면 1 아니면 0 유지
        for(int i=0; i<m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            //양 쪽 다 연결
            arr[x][y] = 1;
            arr[y][x] = 1;
        }
        //연결이 안될시 -1
        answer = -1;
        visited[a] = 1;
        dfs(a,b,0);

        System.out.print(answer);
    }

    //촌수 탐색
    public static void dfs(int start, int target, int cnt){
        if(start == target){
            answer = cnt;
            return;
        }

        for(int i=1; i<=n; i++){
            if(arr[start][i] == 1 && visited[i] == 0){
                visited[i] = 1;
                dfs(i,target,cnt+1);
                visited[i] = 0;
            }
        }
    }

    /**
     * bfs 풀이
     *
     * static ArrayList<Integer>[] graph;  // 그래프를 인접 리스트로 표현
     *     static int[] distance;  // 각 사람(노드)의 촌수를 저장
     *     static boolean[] visited;  // 방문 여부 체크
     *
     *     public static void main(String[] args) {
     *         Scanner sc = new Scanner(System.in);
     *
     *         int n = sc.nextInt();  // 전체 사람의 수
     *         int person1 = sc.nextInt();  // 촌수를 계산할 첫 번째 사람
     *         int person2 = sc.nextInt();  // 촌수를 계산할 두 번째 사람
     *         int m = sc.nextInt();  // 부모 자식간의 관계의 개수
     *
     *         // 그래프 초기화
     *         graph = new ArrayList[n + 1];
     *         for (int i = 1; i <= n; i++) {
     *             graph[i] = new ArrayList<>();
     *         }
     *
     *         // 관계 입력 받기 (부모 자식 관계)
     *         for (int i = 0; i < m; i++) {
     *             int x = sc.nextInt();  // 부모
     *             int y = sc.nextInt();  // 자식
     *             graph[x].add(y);  // 양방향 연결
     *             graph[y].add(x);
     *         }
     *
     *         // BFS로 촌수 계산
     *         distance = new int[n + 1];
     *         visited = new boolean[n + 1];
     *
     *         int result = bfs(person1, person2);
     *
     *         // 촌수가 계산되지 않으면 -1 출력
     *         System.out.println(result);
     *     }
     *
     *     public static int bfs(int start, int target) {
     *         Queue<Integer> queue = new LinkedList<>();
     *         queue.offer(start);
     *         visited[start] = true;
     *         distance[start] = 0;
     *
     *         while (!queue.isEmpty()) {
     *             int current = queue.poll();
     *
     *             // 목표 사람에 도달하면 촌수 반환
     *             if (current == target) {
     *                 return distance[current];
     *             }
     *
     *             // 현재 사람과 연결된 사람들을 탐색
     *             for (int neighbor : graph[current]) {
     *                 if (!visited[neighbor]) {
     *                     visited[neighbor] = true;
     *                     distance[neighbor] = distance[current] + 1;  // 촌수 1 증가
     *                     queue.offer(neighbor);
     *                 }
     *             }
     *         }
     *
     *         // 연결되지 않았을 경우 -1 반환
     *         return -1;
     *     }
     */
}
