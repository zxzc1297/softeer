package boj.etc;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 성준이는 ALOHA의 대표적인 두 대회인 벚꽃컵과 단풍컵을 상징하는 동전을
 * $N$개 가지고 있다. 이 동전의 한쪽 면에는 벚꽃 그림, 반대쪽 면에는 단풍 그림이 그려져 있다.
 *
 * 성준이는 왼쪽에서 오른쪽으로 동전
 * $N$개를 일렬로 내려놓았다. 왼쪽에서부터
 * $i$번째에 놓여 있는 동전이
 * $i$번째 동전이다.
 *
 * 성준이는 단풍이 벚꽃보다 먼저 오는 것에 불만을 가졌다. 따라서 성준이는 원하는 동전을 하나 골라 윗면과 아랫면을 뒤집는 작업을 수행해서, 모든 단풍 그림이 벚꽃 그림보다 오른쪽에 가도록 하려고 한다.
 *
 * 엄밀히 말하면,
 * $(1 \leq i, j \leq N; i \neq j)$를 만족하는 모든 순서쌍
 * $(i,j)$에 대해,
 * $i$번째 동전의 윗면이 벚꽃이고
 * $j$번째 동전의 윗면이 단풍이라면 항상
 * $i < j$를 만족해야 한다.
 *
 * 성준이를 위해 동전을 최소 몇 번 뒤집어야 원하는 대로 동전을 배열할 수 있을지 구해보자.
 *
 * 입력
 * 첫째 줄에 동전의 개수
 * $N$이 주어진다.
 * $(1\leq N\leq 100\,000)$ 
 *
 * 둘째 줄에 B와 D만으로 이루어진 길이가
 * $N$인 문자열
 * $S$가 주어진다.
 *
 * 모든
 * $1\leq i\leq N$에 대해,
 * $S$의
 * $i$번째 문자는
 * $i$번째 동전이 벚꽃 그림이 위에 보이도록 놓여 있으면 B이고, 단풍 그림이 보이도록 놓여 있으면 D이다.
 *
 * 출력
 * 성준이가 원하는 상태를 만들기 위해 동전을 뒤집어야 하는 최소 횟수를 출력한다.
 *
 * 예제 입력 1
 * 7
 * BDBBDBD
 * 예제 출력 1
 * 2
 */
public class boj_33989 {

    public static void main(String[] args) throws IOException {
        // 입력 최적화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String S = br.readLine();

        // 누적합 배열 생성
        int[] prefixD = new int[N + 1]; // D 개수 누적합
        int[] suffixB = new int[N + 1]; // B 개수 누적합 (역순)

        // prefixD[i] : S[0 ~ i-1] 까지 D 개수
        for (int i = 1; i <= N; i++) {
            prefixD[i] = prefixD[i - 1] + (S.charAt(i - 1) == 'D' ? 1 : 0);
        }

        // suffixB[i] : S[i ~ N-1] 까지 B 개수
        for (int i = N - 1; i >= 0; i--) {
            suffixB[i] = suffixB[i + 1] + (S.charAt(i) == 'B' ? 1 : 0);
        }

        int minFlip = Integer.MAX_VALUE;

        // 경계점 i를 기준으로: [0 ~ i-1]은 모두 B, [i ~ N-1]은 모두 D
        for (int i = 0; i <= N; i++) {
            int flips = prefixD[i] + suffixB[i];
            minFlip = Math.min(minFlip, flips);
        }

        System.out.println(minFlip);
    }
}
