package boj.binarySearch;
import java.util.*;

/**
 * 문제
 * 전북대학교 프로그래밍 경진 대회에서는 ACM-ICPC 스타일을 따라 문제를 해결한 팀에게 그 문제에 해당하는 풍선을 달아준다.
 *
 * 풍선을 담당하는 N명의 스태프가 있다. 스태프마다 폐활량이 다르기 때문에 하나의 풍선을 만드는 데 걸리는 시간은 다양하다.
 *
 * 대회가 시작되고 운영진으로부터 M개의 풍선을 만들어 달라는 의뢰가 들어왔다!
 *
 * 각 스태프가 풍선 하나를 만드는 시간(분) Ai를 알고 있을 때, 풍선 M개를 만들기 위해서 최소 몇 분이 걸릴까?
 *
 * 풍선을 만든 후에 문제를 표시할 것이기 때문에 풍선의 종류는 상관이 없다.
 *
 * 모든 스태프는 풍선을 만드는 데에 정확하게 자신이 말한 시간만큼 걸린다. 예를 들어 스태프 A가 풍선 하나를 만드는 데 5분이 걸린다면, 0분에 만들기 시작해서 5분에 끝난다.
 *
 * 입력
 * 스태프의 수 N과 풍선의 개수 M이 입력된다. (1 ≤ N, M ≤ 1 000 000)
 *
 * 다음 줄에 N명의 각 스태프들이 풍선 하나를 만드는 데 걸리는 시간 Ai가 순서대로 주어진다. Ai는 1 000 000 이하의 자연수이다.
 *
 * 출력
 * M개의 풍선이 다 만들어지는 최소 시간을 출력한다.
 */
public class boj_15810 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 스태프 수
        int M = sc.nextInt(); // 만들어야 할 풍선 개수
        int[] times = new int[N];

        int minTime = Integer.MAX_VALUE; // 최소 제작 시간
        for (int i = 0; i < N; i++) {
            times[i] = sc.nextInt();
            minTime = Math.min(minTime, times[i]); // 가장 빠른 시간 찾기
        }

        long left = minTime; // 최소 시간
        long right = (long) minTime * M; // 최대 시간
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0; // mid 시간 내에 만들 수 있는 풍선 개수

            for (int time : times) {
                count += mid / time;
                if (count >= M) break; // M개 이상이면 더 확인할 필요 없음
            }

            if (count >= M) { // M개 이상 만들 수 있으면 시간 줄이기
                answer = mid;
                right = mid - 1;
            } else { // 부족하면 시간 늘리기
                left = mid + 1;
            }
        }

        System.out.println(answer);
        sc.close();
    }
}
