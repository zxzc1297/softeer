package boj.etc;
import java.util.*;

/**
 * 문제
 * 초기에
 * $n+1$개의 집합
 * $\{0\}, \{1\}, \{2\}, \dots , \{n\}$이 있다. 여기에 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다.
 *
 * 집합을 표현하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에
 * $n$,
 * $m$이 주어진다.
 * $m$은 입력으로 주어지는 연산의 개수이다. 다음
 * $m$개의 줄에는 각각의 연산이 주어진다. 합집합은
 * $0$
 * $a$
 * $b$의 형태로 입력이 주어진다. 이는
 * $a$가 포함되어 있는 집합과,
 * $b$가 포함되어 있는 집합을 합친다는 의미이다. 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은
 * $1$
 * $a$
 * $b$의 형태로 입력이 주어진다. 이는
 * $a$와
 * $b$가 같은 집합에 포함되어 있는지를 확인하는 연산이다.
 *
 * 출력
 * 1로 시작하는 입력에 대해서
 * $a$와
 * $b$가 같은 집합에 포함되어 있으면 "YES" 또는 "yes"를, 그렇지 않다면 "NO" 또는 "no"를 한 줄에 하나씩 출력한다.
 *
 * 제한
 *
 * $1 ≤ n ≤ 1\,000\,000$ 
 *
 * $1 ≤ m ≤ 100\,000$ 
 *
 * $0 ≤ a, b ≤ n$ 
 *
 * $a$,
 * $b$는 정수
 *
 * $a$와
 * $b$는 같을 수도 있다.
 * 예제 입력 1
 * 7 8
 * 0 1 3
 * 1 1 7
 * 0 7 6
 * 1 7 1
 * 0 3 7
 * 0 4 2
 * 0 1 1
 * 1 1 1
 * 예제 출력 1
 * NO
 * NO
 * YES
 */
public class boj_1717 {
    static int[] parent;

    // Find 연산: 루트를 찾음 (경로 압축 최적화 포함)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    // Union 연산: 두 집합을 합침
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA; // 한쪽의 루트를 다른 쪽에 연결
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 처리
        int n = sc.nextInt(); // 집합의 원소 개수
        int m = sc.nextInt(); // 연산의 개수

        // 부모 배열 초기화
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i; // 초기에는 각 원소가 자기 자신을 부모로 가짐
        }

        // 연산 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int op = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (op == 0) {
                // 합집합 연산
                union(a, b);
            } else if (op == 1) {
                // 같은 집합 여부 확인
                if (find(a) == find(b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        // 결과 출력
        System.out.print(sb);

        sc.close();
    }
}
