package boj.binarySearch;
import java.util.*;

/**
 * 문제
 *
 *
 * 평소 요리에 관심이 많은 승균이는 치킨집을 개업하였다. 승균이네 치킨집은 파닭이 주메뉴다. 승균이는 가게를 오픈하기 전에 남부시장에 들러서 길이가 일정하지 않은 파를 여러 개 구매하였다. 승균이는 파닭의 일정한 맛을 유지하기 위해 각각의 파닭에 같은 양의 파를 넣는다. 또 파닭 맛은 파의 양에 따라 좌우된다고 생각하기 때문에 될 수 있는 한 파의 양을 최대한 많이 넣으려고 한다. (하지만 하나의 파닭에는 하나 이상의 파가 들어가면 안 된다.) 힘든 하루를 마치고 승균이는 파닭을 만들고 남은 파를 라면에 넣어 먹으려고 한다. 이때 라면에 넣을 파의 양을 구하는 프로그램을 작성하시오. 승균이네 치킨집 자는 정수만 표현되기 때문에 정수의 크기로만 자를 수 있다.
 *
 * 입력
 * 첫째 줄에 승균이가 시장에서 사 온 파의 개수 S(1 ≤ S ≤ 1,000,000), 그리고 주문받은 파닭의 수 C(1 ≤ C ≤ 1,000,000)가 입력된다. 파의 개수는 항상 파닭의 수를 넘지 않는다. (S ≤ C) 그 후, S 줄에 걸쳐 파의 길이 L(1 ≤ L ≤ 1,000,000,000)이 정수로 입력된다.
 *
 * 출력
 * 승균이가 라면에 넣을 파의 양을 출력한다.
 *
 * 예제 입력 1
 * 3 5
 * 440
 * 350
 * 230
 * 예제 출력 1
 * 145
 */
public class boj_14627 {
    static int S, C;
    static int[] leeks;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();  // 파의 개수
        C = sc.nextInt();  // 치킨 개수
        leeks = new int[S];

        long totalLeeks = 0; // 전체 파의 길이 합
        int maxLeek = 0; // 가장 긴 파의 길이

        for (int i = 0; i < S; i++) {
            leeks[i] = sc.nextInt();
            totalLeeks += leeks[i];
            maxLeek = Math.max(maxLeek, leeks[i]);
        }
        sc.close();

        // 1 ~ maxLeek 사이에서 최대 길이 찾기 (이분 탐색)
        int left = 1, right = maxLeek;
        int bestLength = 0; // 최적의 파 길이

        while (left <= right) {
            int mid = (left + right) / 2; // 현재 시도하는 파 길이
            long count = 0; // 만들 수 있는 파닭 개수

            for (int leek : leeks) {
                count += leek / mid; // 현재 길이로 몇 개 만들 수 있는지
            }

            if (count >= C) { // C개 이상 만들 수 있으면
                bestLength = mid;
                left = mid + 1; // 더 긴 길이 시도
            } else { // 부족하면 길이 줄이기
                right = mid - 1;
            }
        }

        // 사용된 파의 총 길이는 bestLength * C
        long usedLeeks = (long) bestLength * C;

        // 남은 파의 양 계산
        long remainingLeeks = totalLeeks - usedLeeks;
        System.out.println(remainingLeeks);
    }
}
