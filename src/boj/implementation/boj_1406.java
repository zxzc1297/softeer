package boj.implementation;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 한 줄로 된 간단한 에디터를 구현하려고 한다. 이 편집기는 영어 소문자만을 기록할 수 있는 편집기로, 최대 600,000글자까지 입력할 수 있다.
 *
 * 이 편집기에는 '커서'라는 것이 있는데, 커서는 문장의 맨 앞(첫 번째 문자의 왼쪽), 문장의 맨 뒤(마지막 문자의 오른쪽), 또는 문장 중간 임의의 곳(모든 연속된 두 문자 사이)에 위치할 수 있다. 즉 길이가 L인 문자열이 현재 편집기에 입력되어 있으면, 커서가 위치할 수 있는 곳은 L+1가지 경우가 있다.
 *
 * 이 편집기가 지원하는 명령어는 다음과 같다.
 *
 * L	커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
 * D	커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
 * B	커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
 * 삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
 * P $	$라는 문자를 커서 왼쪽에 추가함
 * 초기에 편집기에 입력되어 있는 문자열이 주어지고, 그 이후 입력한 명령어가 차례로 주어졌을 때, 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 구하는 프로그램을 작성하시오. 단, 명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치하고 있다고 한다.
 *
 * 입력
 * 첫째 줄에는 초기에 편집기에 입력되어 있는 문자열이 주어진다. 이 문자열은 길이가 N이고, 영어 소문자로만 이루어져 있으며, 길이는 100,000을 넘지 않는다. 둘째 줄에는 입력할 명령어의 개수를 나타내는 정수 M(1 ≤ M ≤ 500,000)이 주어진다. 셋째 줄부터 M개의 줄에 걸쳐 입력할 명령어가 순서대로 주어진다. 명령어는 위의 네 가지 중 하나의 형태로만 주어진다.
 *
 * 출력
 * 첫째 줄에 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 출력한다.
 *
 * 예제 입력 1
 * abcd
 * 3
 * P x
 * L
 * P y
 * 예제 출력 1
 * abcdyx
 * 예제 입력 2
 * abc
 * 9
 * L
 * L
 * L
 * L
 * L
 * P x
 * L
 * B
 * P y
 * 예제 출력 2
 * yxabc
 */
public class boj_1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        // 초기 문자열 입력
        String initialString = br.readLine();
        int commandCount = Integer.parseInt(br.readLine());

        // LinkedList와 ListIterator 사용
        LinkedList<Character> list = new LinkedList<>();
        for (char ch : initialString.toCharArray()) {
            list.add(ch);
        }
        ListIterator<Character> iterator = list.listIterator(list.size()); // 커서를 맨 뒤로 이동

        // 명령어 처리
        for (int i = 0; i < commandCount; i++) {
            String command = br.readLine();

            switch (command.charAt(0)) {
                case 'L': // 커서를 왼쪽으로 이동
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                    }
                    break;
                case 'D': // 커서를 오른쪽으로 이동
                    if (iterator.hasNext()) {
                        iterator.next();
                    }
                    break;
                case 'B': // 왼쪽 문자 삭제
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                        iterator.remove();
                    }
                    break;
                case 'P': // 왼쪽에 문자 추가
                    char toAdd = command.charAt(2);
                    iterator.add(toAdd);
                    break;
            }
        }

        // 결과 문자열 생성
        for (char ch : list) {
            result.append(ch);
        }

        // 결과 출력
        System.out.println(result.toString());
    }
}
