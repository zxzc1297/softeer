package boj.greedy;
import java.util.*;

/**
 * 문제
 * 지민이는 항구에서 일한다. 그리고 화물을 배에 실어야 한다. 모든 화물은 박스에 안에 넣어져 있다. 항구에는 크레인이 N대 있고, 1분에 박스를 하나씩 배에 실을 수 있다. 모든 크레인은 동시에 움직인다.
 *
 * 각 크레인은 무게 제한이 있다. 이 무게 제한보다 무거운 박스는 크레인으로 움직일 수 없다. 모든 박스를 배로 옮기는데 드는 시간의 최솟값을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 각 크레인의 무게 제한이 주어진다. 이 값은 1,000,000보다 작거나 같다. 셋째 줄에는 박스의 수 M이 주어진다. M은 10,000보다 작거나 같은 자연수이다. 넷째 줄에는 각 박스의 무게가 주어진다. 이 값도 1,000,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 모든 박스를 배로 옮기는데 드는 시간의 최솟값을 출력한다. 만약 모든 박스를 배로 옮길 수 없으면 -1을 출력한다.
 *
 * 예제 입력 1
 * 3
 * 6 8 9
 * 5
 * 2 5 2 4 7
 * 예제 출력 1
 * 2
 * 예제 입력 2
 * 2
 * 19 20
 * 7
 * 14 12 16 19 16 1 5
 * 예제 출력 2
 * 4
 * 예제 입력 3
 * 4
 * 23 32 25 28
 * 10
 * 5 27 10 16 24 20 2 32 18 7
 * 예제 출력 3
 * 3
 * 예제 입력 4
 * 10
 * 11 17 5 2 20 7 5 5 20 7
 * 5
 * 18 18 15 15 17
 * 예제 출력 4
 * 2
 */
public class boj_1092 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 크레인 수 입력
        int n = sc.nextInt();
        Integer[] cranes = new Integer[n];
        for (int i = 0; i < n; i++) {
            cranes[i] = sc.nextInt();
        }

        // 박스 수 입력
        int m = sc.nextInt();
        Integer[] boxes = new Integer[m];
        for (int i = 0; i < m; i++) {
            boxes[i] = sc.nextInt();
        }

        // 내림차순 정렬
        Arrays.sort(cranes, Collections.reverseOrder());
        Arrays.sort(boxes, Collections.reverseOrder());

        // 가장 무거운 박스를 가장 강한 크레인이 들 수 없으면 불가능
        if (boxes[0] > cranes[0]) {
            System.out.println(-1);
            return;
        }

        int time = 0;

        // 박스를 이동할 때마다 관리
        List<Integer> remainingBoxes = new ArrayList<>(Arrays.asList(boxes));

        while (!remainingBoxes.isEmpty()) {
            int craneIndex = 0;

            // 각 크레인으로 박스 처리
            for (int i = 0; i < remainingBoxes.size(); ) {
                if (craneIndex == n) break;

                // 크레인이 박스를 들 수 있는 경우
                if (remainingBoxes.get(i) <= cranes[craneIndex]) {
                    remainingBoxes.remove(i);
                    craneIndex++;
                } else {
                    i++;
                }
            }

            time++;
        }

        System.out.println(time);
    }
}
