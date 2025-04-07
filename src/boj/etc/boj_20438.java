package boj.etc;
import java.util.*;

/**
 * 문제
 * 코로나 바이러스로 인해 H 대학은 비대면 강의를 실시하고 있다. 조교를 담당하게 된 지환이는 출석체크 방식을 바꾸려고 한다.
 *
 * 학생들은 접속 순서대로 3번부터 N + 2번까지 입장 번호를 받게 된다.
 *
 * 지환이가 한 학생에게 출석 코드를 보내게 되면, 해당 학생은 본인의 입장 번호의 배수인 학생들에게 출석 코드를 보내어 해당 강의의 출석을 할 수 있게끔 한다.
 *
 * 하지만, K명의 졸고 있는 학생들은 출석 코드를 제출하지 않고, 다른 학생들에게 보내지 않는다.
 *
 * 지환이는 무작위로 한 명의 학생에게 출석 코드를 보내는 행위를 Q번 반복한 뒤, 출석부 정리를 위해 특정 구간의 입장 번호를 받은 학생들 중에서 출석이 되지 않은 학생들의 수를 구하고 싶다.
 *
 * 많은 인원을 담당해서 바쁜 지환이를 위해 프로그램을 만들어주자!
 *
 * 입력
 * 1번째 줄에 학생의 수 N, 졸고 있는 학생의 수 K, 지환이가 출석 코드를 보낼 학생의 수 Q, 주어질 구간의 수 M이 주어진다. (1 ≤ K, Q ≤ N ≤ 5,000, 1 ≤ M ≤ 50,000)
 *
 * 2번째 줄과 3번째 줄에 각각 K명의 졸고 있는 학생의 입장 번호들과 Q명의 출석 코드를 받을 학생의 입장 번호들이 주어진다.
 *
 * 4번째 줄부터 M개의 줄 동안 구간의 범위 S, E가 공백을 사이에 두고 주어진다. (3 ≤ S < E ≤ N + 2)
 *
 * 출력
 * M개의 줄에 걸쳐서 각 구간에 대해서 출석이 되지 않은 학생들의 수를 출력하라.
 *
 * 예제 입력 1
 * 10 1 3 1
 * 7
 * 3 5 7
 * 3 12
 * 예제 출력 1
 * 4
 * 입장 번호 3번부터 12번까지의 구간에서 입장 번호 4, 8, 11번이 출석 코드를 받지 못했고, 7번은 출석 코드를 받았으나 조느라 출석하지 못했다.
 *
 * 예제 입력 2
 * 50 4 5 1
 * 24 15 27 43
 * 3 4 6 20 25
 * 3 52
 * 예제 출력 2
 * 25
 */
public class boj_20438 {
    static final int MAX = 5003; // 최대 입장번호는 N + 2이므로 넉넉히
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 학생 수
        int K = sc.nextInt(); // 졸고 있는 학생 수
        int Q = sc.nextInt(); // 출석 코드 받을 학생 수
        int M = sc.nextInt(); // 쿼리 수

        boolean[] sleeping = new boolean[MAX];  // 졸고 있는 학생 체크
        boolean[] attendance = new boolean[MAX]; // 출석 여부
        int[] prefix = new int[MAX]; // 출석 누적합

        // 졸고 있는 학생 처리
        for (int i = 0; i < K; i++) {
            int num = sc.nextInt();
            sleeping[num] = true;
        }

        // 출석 코드 받은 학생 처리
        for (int i = 0; i < Q; i++) {
            int num = sc.nextInt();
            if (sleeping[num]) continue; // 졸고 있는 학생은 무시

            // 해당 학생의 배수인 사람들에게 출석 코드 전파
            for (int j = num; j < MAX; j += num) {
                if (!sleeping[j]) {
                    attendance[j] = true;
                }
            }
        }

        // 누적합 배열 생성 (출석하지 않은 학생 수 누적)
        for (int i = 3; i < MAX; i++) {
            prefix[i] = prefix[i - 1] + (attendance[i] ? 0 : 1);
        }

        // 쿼리 처리
        for (int i = 0; i < M; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            System.out.println(prefix[E] - prefix[S - 1]);
        }
    }
}
