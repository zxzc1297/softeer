package boj.binarySearch;
import java.util.*;

/**
 * 문제
 * 도현이의 집 N개가 수직선 위에 있다. 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
 *
 * 도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다. 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에, 한 집에는 공유기를 하나만 설치할 수 있고, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
 *
 * C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다. 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.
 *
 * 출력
 * 첫째 줄에 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.
 *
 * 예제 입력 1
 * 5 3
 * 1
 * 2
 * 8
 * 4
 * 9
 * 예제 출력 1
 * 3
 * 힌트
 * 공유기를 1, 4, 8 또는 1, 4, 9에 설치하면 가장 인접한 두 공유기 사이의 거리는 3이고, 이 거리보다 크게 공유기를 3개 설치할 수 없다.
 */
public class boj_2110 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 집의 개수
        int c = sc.nextInt(); // 공유기의 개수

        int[] houses = new int[n];

        for (int i = 0; i < n; i++) {
            houses[i] = sc.nextInt();
        }

        // 집의 좌표를 정렬
        Arrays.sort(houses);

        int left = 1; // 최소 거리
        int right = houses[n - 1] - houses[0]; // 최대 거리
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // 공유기 간의 거리

            if (canPlaceRouters(houses, c, mid)) {
                result = mid; // 조건을 만족하면 거리 증가 시도
                left = mid + 1;
            } else {
                right = mid - 1; // 조건을 만족하지 않으면 거리 감소 시도
            }
        }

        System.out.println(result);
    }

    // mid 거리로 공유기를 배치할 수 있는지 확인
    public static boolean canPlaceRouters(int[] houses, int c, int distance) {
        int count = 1; // 첫 번째 공유기는 첫 집에 설치
        int lastPosition = houses[0];

        for (int i = 1; i < houses.length; i++) {
            if (houses[i] - lastPosition >= distance) {
                count++;
                lastPosition = houses[i];
            }

            if (count >= c) {
                return true;
            }
        }

        return false;
    }
}
