package boj.dp;
import java.util.*;

/**
 * 문제
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 *
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * X가 2로 나누어 떨어지면, 2로 나눈다.
 * 1을 뺀다.
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 *
 * 입력
 * 첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 자연수 N이 주어진다.
 *
 * 출력
 * 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 *
 * 둘째 줄에는 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다. 정답이 여러 가지인 경우에는 아무거나 출력한다.
 *
 * 예제 입력 1
 * 2
 * 예제 출력 1
 * 1
 * 2 1
 * 예제 입력 2
 * 10
 * 예제 출력 2
 * 3
 * 10 9 3 1
 */
public class boj_12852 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n + 1];     // 각 숫자까지 도달하는 최소 연산 횟수
        int[] from = new int[n + 1];   // 경로 추적용

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        dp[n] = 0; // 시작점은 0번 연산

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == 1) break; // 1에 도달하면 종료

            // 다음 가능한 연산들
            int[] next = {cur - 1, (cur % 2 == 0) ? cur / 2 : -1, (cur % 3 == 0) ? cur / 3 : -1};

            for (int nextNum : next) {
                if (nextNum != -1 && dp[nextNum] == 0) {
                    dp[nextNum] = dp[cur] + 1;
                    from[nextNum] = cur; // 어디서 왔는지 저장
                    queue.offer(nextNum);
                }
            }
        }

        // 연산 횟수 출력
        System.out.println(dp[1]);

        // 경로 추적
        List<Integer> path = new ArrayList<>();
        for (int i = 1; i != 0; i = from[i]) {
            path.add(i);
        }
        Collections.reverse(path);
        for (int num : path) {
            System.out.print(num + " ");
        }
    }
}
