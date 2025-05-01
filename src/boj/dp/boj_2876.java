package boj.dp;
import java.util.*;

/**
 * 문제
 * 오늘은 기초컴퓨터그래픽스의 퀴즈가 있는 날이다. 기다란 교실 안에는 N개의 책상이 한 줄로 늘어서 있는데, 각 책상당 두 명의 학생이 앉도록 되어있다.
 *
 * 모든 학생들은 그래픽스를  열심히 공부했지만, 말도 안되는 난이도에 질려 포기하고 말았다. 한편 교수님은 각 학생들의 얼굴만 보고도 이 학생이 받아야 할 그레이드를 정확히 알아낼 수 있다.
 *
 * 교수님은 그래픽스 과목을 가르치는 만큼 자신의 미적 감각을 살리기 위해 각 그레이드를 다른 색을 이용해서 표시한다(예를 들어 A를 빨강으로 칠하면, B,C,D는 빨강으로 표시하지 않는다).
 *
 * 또, 퀴즈의 방식은 교수님이 수업이 시작할 때 어떤 두 책상을 선택하고, 두 책상과 그 사이에 있는 모든 책상에서 각각 한 명씩 지목해서 질문을 하고, 학생의 대답을 듣는 것이다.
 *
 * 오늘 교수님은 바쁜 나머지 한 가지 색의 색연필만 가지고 왔고, 결국 자신의 미학을 지키기 위해 퀴즈에서 지목한 모두에게 같은 그레이드를 주려고 한다. 교수님이 채점할 수 있는 학생의 수는 최대 몇 명일까?
 *
 * 입력
 * 입력의 첫 번째 줄에는 정수 N이 주어진다(1 ≤ N ≤ 100,000).
 *
 * 다음 N개의 줄에는 i번째 책상에 앉은 두 학생이 받아야 할 그레이드 Ai와 Bi(1 ≤ Ai, Bi ≤ 5)가 주어진다.
 *
 * 출력
 * 교수님이 한 가지 색만을 이용해 채점할 수 있는 최대 학생 수와 그때의 그레이드를 출력한다.
 *
 * 만약 답이 여러 가지라면, 가장 작은 그레이드를 출력한다.
 *
 * 예제 입력 1
 * 1
 * 1 5
 * 예제 출력 1
 * 1 1
 * 예제 입력 2
 * 3
 * 3 5
 * 4 5
 * 1 3
 * 예제 출력 2
 * 2 5
 * 예제 입력 3
 * 4
 * 2 1
 * 3 2
 * 5 3
 * 2 5
 * 예제 출력 3
 * 2 2
 */
public class boj_2876 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] desks = new int[N][2];

        for (int i = 0; i < N; i++) {
            desks[i][0] = sc.nextInt();
            desks[i][1] = sc.nextInt();
        }

        int maxCount = 0;
        int bestGrade = 6; // 초기값 6 (1~5보다 큼)

        // 그레이드 1~5 각각에 대해 탐색
        for (int grade = 1; grade <= 5; grade++) {
            int left = 0;
            int right = 0;
            while (right < N) {
                // 조건 만족 시 오른쪽 확장
                if (desks[right][0] == grade || desks[right][1] == grade) {
                    right++;
                    int count = right - left; // 현재 구간 길이 = 학생 수
                    if (count > maxCount || (count == maxCount && grade < bestGrade)) {
                        maxCount = count;
                        bestGrade = grade;
                    }
                } else {
                    // 조건 안 맞으면 왼쪽을 앞으로 이동
                    right++;
                    left = right;
                }
            }
        }

        System.out.println(maxCount + " " + bestGrade);
    }
}
