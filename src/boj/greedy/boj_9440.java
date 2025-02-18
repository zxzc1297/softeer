package boj.greedy;
import java.util.*;

/**
 * 문제
 * 강민이가 초등학교 3학년일 때, 담임선생님이 이런 문제를 냈었다.
 *
 * 숫자 1, 2, 7, 8, 9 를 사용해서 만든 두 숫자를 더했을 때, 나올 수 있는 가장 작은 수는 무엇일까요?
 * 강민이는 이 문제의 답이 207(78 + 129)이라고 생각했다. 그런데 선생님은 책 4페이지에 있는 비슷한 문제를 모두 풀어오라는 숙제를 내셨다.
 *
 * 작년부터 프로그래밍을 시작한 강민이는 이런 숙제보다 코딩을 더 재밌어했다. 그래서 강민이는 이 숙제를 코딩으로 해결하기로 했다!
 *
 * 어린 강민이를 위해 코딩을 도와주자.
 *
 * 입력
 * 한 줄에 하나씩 연습문제가 주어진다.
 *
 * 각 줄에서 첫 번째로 나오는 정수 N (2 ≤ N ≤ 14) 은 연습문제에서 사용될 숫자의 개수이다.
 *
 * 두 번째부터 사용될 N개의 숫자가 주어진다. 0이 아닌 수가 최소 2개 이상 존재한다
 *
 * 마지막 줄에 0을 입력하면 프로그램이 종료된다.
 *
 * 출력
 * 각 연습문제마다 정답을 출력한다.
 *
 * 예제 입력 1
 * 5 1 2 7 8 9
 * 6 3 4 2 2 2 2
 * 9 0 1 2 3 4 0 1 2 3
 * 0
 * 예제 출력 1
 * 207
 * 447
 * 11257
 */
public class boj_9440 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int N = sc.nextInt();
            if (N == 0) break; // 종료 조건

            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                numbers.add(sc.nextInt());
            }

            Collections.sort(numbers); // 숫자 정렬

            // 최소합을 만들 두 개의 숫자
            StringBuilder num1 = new StringBuilder();
            StringBuilder num2 = new StringBuilder();

            // 0이 아닌 최소 숫자를 찾아 첫 번째 숫자의 맨 앞에 배치
            int nonZeroIndex = 0;
            while (numbers.get(nonZeroIndex) == 0) {
                nonZeroIndex++;
            }

            num1.append(numbers.get(nonZeroIndex));
            numbers.remove(nonZeroIndex); // 사용한 숫자 제거

            // 다음으로 0이 아닌 최소 숫자를 찾아 두 번째 숫자의 맨 앞에 배치
            nonZeroIndex = 0;
            while (numbers.get(nonZeroIndex) == 0) {
                nonZeroIndex++;
            }

            num2.append(numbers.get(nonZeroIndex));
            numbers.remove(nonZeroIndex); // 사용한 숫자 제거

            // 남은 숫자를 번갈아 가며 배치하여 두 개의 수 생성
            for (int i = 0; i < numbers.size(); i++) {
                if (i % 2 == 0) {
                    num1.append(numbers.get(i));
                } else {
                    num2.append(numbers.get(i));
                }
            }

            // 최소 합 출력
            int result = Integer.parseInt(num1.toString()) + Integer.parseInt(num2.toString());
            System.out.println(result);
        }
        sc.close();
    }
}
