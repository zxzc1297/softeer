package boj.sort;
import java.util.*;

/**
 * 문제
 * n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열이 있다. ai의 값은 1보다 크거나 같고, 1000000보다 작거나 같은 자연수이다. 자연수 x가 주어졌을 때, ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 수열의 크기 n이 주어진다. 다음 줄에는 수열에 포함되는 수가 주어진다. 셋째 줄에는 x가 주어진다. (1 ≤ n ≤ 100000, 1 ≤ x ≤ 2000000)
 *
 * 출력
 * 문제의 조건을 만족하는 쌍의 개수를 출력한다.
 *
 * 예제 입력 1
 * 9
 * 5 12 7 10 9 1 2 3 11
 * 13
 * 예제 출력 1
 * 3
 */
public class boj_3273 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 수열의 크기
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int x = sc.nextInt(); // 목표 합
        sc.close();

        Arrays.sort(arr); // 정렬 (O(N log N))

        int left = 0, right = n - 1, count = 0;

        // 투 포인터 탐색 (O(N))
        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == x) { // 조건 만족
                count++;
                left++;
                right--;
            } else if (sum < x) { // 합이 부족하면 왼쪽 증가
                left++;
            } else { // 합이 크면 오른쪽 감소
                right--;
            }
        }

        System.out.println(count);
    }
}
