package boj.etc;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 숭실골 높은 언덕 깊은 골짜기에 출제로 고통 받는 욱제가 살고 있다!
 *
 * 욱제는 또 출제를 해야 해서 단단히 화가 났다. 그래서 욱제는 길이 N짜리 수열 A를 만들고, A를 비내림차순으로 정렬해서 수열 B를 만들어 버렸다!! 여기서 B를 출력하기만 하면 문제가 너무 쉬우니까 하나만 더 하자. 아래와 같은 질문이 무려 Q개나 주어진다!! (ㅎㅎ;; ㅈㅅ.. ㅋㅋ!!)
 *
 * L R: BL + BL+1 + ... + BR-1 + BR 을 출력한다.
 *
 *
 * Figure 1. 모든 참가자가 문제를 풀 수 있을 것이라고 기대하는 욱제의 표정
 *
 * 욱제의 질문에 답하고 함께 엠티를 떠나자!!
 *
 * 입력
 * 첫 번째 줄에 수열 A의 길이 N과 질문의 개수 Q가 공백으로 구분되어 주어진다. (1 ≤ N, Q ≤ 300,000)
 *
 * 두 번째 줄에 N개의 정수 A1, A2, ..., AN 이 공백으로 구분되어 주어진다. Ai 는 수열 A의 i 번째 수이다. (1 ≤ Ai ≤ 1,000)
 *
 * 세 번째 줄부터 Q개의 줄에 걸쳐 욱제의 질문을 의미하는 두 수 L, R이 공백으로 구분되어 주어진다. (1 ≤ L ≤ R ≤ N)
 *
 * 주어지는 모든 입력은 자연수이다.
 *
 * 출력
 * Q개의 줄에 걸쳐, 질문의 답을 순서대로 각각 출력한다.
 *
 * 예제 입력 1
 * 5 6
 * 2 5 1 4 3
 * 1 5
 * 2 4
 * 3 3
 * 1 3
 * 2 5
 * 4 5
 * 예제 출력 1
 * 15
 * 9
 * 3
 * 6
 * 14
 * 9
 * [2, 5, 1, 4, 3]을 비내림차순으로 정렬하면 [1, 2, 3, 4, 5]이다.

 */
public class boj_17390 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 수열 A를 정렬해서 B 생성
        Arrays.sort(A);

        // 누적 합 계산
        long[] prefixSum = new long[N + 1]; // 1-based 인덱스
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i - 1];
        }

        // 출력 준비
        StringBuilder sb = new StringBuilder();

        // 질의 처리
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long sum = prefixSum[R] - prefixSum[L - 1];
            sb.append(sum).append("\n");
        }

        // 출력
        System.out.print(sb.toString());
    }
}
