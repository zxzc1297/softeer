package boj.etc;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 닝닝이는 예, 아니오로 답할 수 있는 질문을 좋아한다. 닝닝이 한 첫 번째 질문의 답은 "예"였다.
 *
 * 이후, 2번째부터 닝닝이는 다음과 같은 형태의 질문을 계속해서 할 것이다.
 *
 * 1 x y :
 * $x$번째 질문부터
 * $y$번째 질문의 답이 모두 "예"였습니까?
 * 2 x y :
 * $x$번째 질문부터
 * $y$번째 질문의 답이 모두 "아니오"였습니까?
 * 닝닝이가 위 질문을 하는 시점에, 당신은 이미 닝닝이가 한
 * $x$번째 질문부터
 * $y$번째 질문에 답한 적이 있다. 닝닝이가 하는 질문에 모두 답하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에, 닝닝이가 한 첫 번째 질문을 제외한 질문의 개수
 * $N$이 주어진다. 즉, 닝닝이는 총
 * $N+1$개의 질문을 했다.
 *
 * 이후
 * $N$개의 줄에, 닝닝이가 한 각 질문이 차례대로 1 x y 또는 2 x y의 형태로 주어진다.
 *
 * 출력
 *
 * $N$개의 줄에 걸쳐, 닝닝이가 한 질문의 답을 순서대로 출력한다. 각각의 답이 "예"라면 Yes를, "아니오"라면 No를 출력한다.
 *
 * 제한
 * 주어지는 모든 수는 정수이다.
 *
 * $1 \le N \le 200\,000$ 
 *
 * $i$ (
 * $2 \le i \le N+1$)번째 질문에 대해,
 * $1 \le x \le y < i$.
 * 예제 입력 1
 * 6
 * 1 1 1
 * 2 1 2
 * 2 2 3
 * 1 1 2
 * 2 3 4
 * 1 5 5
 * 예제 출력 1
 * Yes
 * No
 * No
 * Yes
 * Yes
 * Yes
 * 첫번째 질문의 답은 "예"이다. (입력에서는 2번째 질문부터 주어짐에 유의하라.)
 * 1번째 1번째 질문의 답이 모두 "예"이므로, 2번째 질문의 답은 "예"이다.
 * 1번째 2번째 질문의 답이 모두 "아니오"이지는 않으므로, 3번째 질문의 답은 "아니오"이다.
 * 2번째 3번째 질문의 답이 모두 "아니오"이지는 않으므로, 4번째 질문의 답은 "아니오"이다.
 * 1번째 2번째 질문의 답이 모두 "예"이므로, 5번째 질문의 답은 "예"이다.
 * 3번째 4번째 질문의 답이 모두 "아니오"이므로, 6번째 질문의 답은 "예"이다.
 * 5번째 5번째 질문의 답이 모두 "예"이므로, 7번째 질문의 답은 "예"이다.
 */
public class boj_32194 {
    public static void main(String[] args) throws IOException {
        // 입력 최적화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 답 저장용 (index 1부터 시작, 총 N+1개 질문)
        int[] answer = new int[N + 2];  // 1-based index, 1번 질문은 무조건 Yes
        answer[1] = 1;

        // 누적합 저장용
        int[] prefixSum = new int[N + 2];
        prefixSum[1] = 1;

        StringBuilder sb = new StringBuilder();

        for (int i = 2; i <= N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int yesCount = prefixSum[y] - prefixSum[x - 1];

            boolean isYes;
            if (type == 1) {
                // 모두 "Yes" 인가?
                isYes = (yesCount == (y - x + 1));
            } else {
                // 모두 "No" 인가?
                isYes = (yesCount == 0);
            }

            answer[i] = isYes ? 1 : 0;
            prefixSum[i] = prefixSum[i - 1] + answer[i];

            sb.append(isYes ? "Yes" : "No").append("\n");
        }

        System.out.print(sb);
    }
}
