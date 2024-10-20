package boj.greedy;
import java.util.*;

/**
 * 문제
 * 민식이는 수학학원에서 단어 수학 문제를 푸는 숙제를 받았다.
 *
 * 단어 수학 문제는 N개의 단어로 이루어져 있으며, 각 단어는 알파벳 대문자로만 이루어져 있다. 이때, 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제이다. 같은 알파벳은 같은 숫자로 바꿔야 하며, 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안 된다.
 *
 * 예를 들어, GCF + ACDEB를 계산한다고 할 때, A = 9, B = 4, C = 8, D = 6, E = 5, F = 3, G = 7로 결정한다면, 두 수의 합은 99437이 되어서 최대가 될 것이다.
 *
 * N개의 단어가 주어졌을 때, 그 수의 합을 최대로 만드는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 단어의 개수 N(1 ≤ N ≤ 10)이 주어진다. 둘째 줄부터 N개의 줄에 단어가 한 줄에 하나씩 주어진다. 단어는 알파벳 대문자로만 이루어져있다. 모든 단어에 포함되어 있는 알파벳은 최대 10개이고, 수의 최대 길이는 8이다. 서로 다른 문자는 서로 다른 숫자를 나타낸다.
 *
 * 출력
 * 첫째 줄에 주어진 단어의 합의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 2
 * AAA
 * AAA
 * 예제 출력 1
 * 1998
 * 예제 입력 2
 * 2
 * GCF
 * ACDEB
 * 예제 출력 2
 * 99437
 * 예제 입력 3
 * 10
 * A
 * B
 * C
 * D
 * E
 * F
 * G
 * H
 * I
 * J
 * 예제 출력 3
 * 45
 * 예제 입력 4
 * 2
 * AB
 * BA
 * 예제 출력 4
 * 187
 */
public class boj_1339 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();  // 개행 문자 제거
        String[] list = new String[n];

        // 각 알파벳의 가중치 계산을 위한 map
        HashMap<Character, Integer> weightMap = new HashMap<>();

        // 각 단어에서 자릿수 별로 가중치를 계산
        for (int i = 0; i < n; i++) {
            String tmp = sc.nextLine();
            list[i] = tmp;
            int len = tmp.length();

            // 자릿수에 따라 알파벳에 가중치 부여
            for (int j = 0; j < len; j++) {
                char c = tmp.charAt(j);
                int weight = (int) Math.pow(10, len - j - 1);
                weightMap.put(c, weightMap.getOrDefault(c, 0) + weight);
            }
        }

        // 가중치가 큰 순서대로 정렬
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(weightMap.entrySet());
        entryList.sort((a, b) -> b.getValue() - a.getValue());  // 내림차순 정렬

        // 가장 큰 가중치부터 9, 8, ... 0 까지 할당
        int num = 9;
        HashMap<Character, Integer> values = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : entryList) {
            values.put(entry.getKey(), num--);
        }

        // 단어들을 숫자로 변환하여 합계 계산
        long answer = 0;
        for (String str : list) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < str.length(); i++) {
                sb.append(values.get(str.charAt(i)));
            }

            answer += Long.parseLong(sb.toString());  // 큰 값을 처리할 수 있게 Long으로 변경
        }

        // 결과 출력
        System.out.print(answer);
    }
}
