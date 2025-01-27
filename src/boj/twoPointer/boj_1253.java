package boj.twoPointer;
import java.util.*;

/**
 * 문제
 * N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 그 수를 “좋다(GOOD)”고 한다.
 *
 * N개의 수가 주어지면 그 중에서 좋은 수의 개수는 몇 개인지 출력하라.
 *
 * 수의 위치가 다르면 값이 같아도 다른 수이다.
 *
 * 입력
 * 첫째 줄에는 수의 개수 N(1 ≤ N ≤ 2,000), 두 번째 줄에는 i번째 수를 나타내는 Ai가 N개 주어진다. (|Ai| ≤ 1,000,000,000, Ai는 정수)
 *
 * 출력
 * 좋은 수의 개수를 첫 번째 줄에 출력한다.
 *
 * 예제 입력 1
 * 10
 * 1 2 3 4 5 6 7 8 9 10
 * 예제 출력 1
 * 8
 */
public class boj_1253 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long[] arr = new long[n];

        // 배열 입력
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        // 배열 정렬
        Arrays.sort(arr);

        int goodCount = 0;

        // 각 수를 "좋다(GOOD)"인지 판단
        for (int k = 0; k < n; k++) {
            long target = arr[k];
            int left = 0;
            int right = n - 1;

            while (left < right) {
                // k번째 수는 제외
                if (left == k) {
                    left++;
                    continue;
                }
                if (right == k) {
                    right--;
                    continue;
                }

                long sum = arr[left] + arr[right];

                if (sum == target) { // 좋은 수 발견
                    goodCount++;
                    break;
                } else if (sum < target) { // 합이 작으면 왼쪽 포인터 증가
                    left++;
                } else { // 합이 크면 오른쪽 포인터 감소
                    right--;
                }
            }
        }

        System.out.println(goodCount);
    }
}
