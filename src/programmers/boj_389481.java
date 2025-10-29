package programmers;
import java.util.*;

/**
 * 문제 설명
 * 어느 날, 전설 속에 전해 내려오는 비밀 주문서가 세상에 다시 모습을 드러냈습니다. 이 주문서에는 마법 세계에서 사용되는 모든 주문이 적혀 있는데, 각 주문은 알파벳 소문자 11글자 이하로 구성되어 있습니다. 주문서에는 실제로 마법적 효과를 지니지 않는 의미 없는 주문들 즉, 알파벳 소문자 11글자 이하로 쓸 수 있는 모든 문자열이 고대의 규칙에 따라 아래와 같이 정렬되어 있습니다.
 *
 * 글자 수가 적은 주문부터 먼저 기록된다.
 * 글자 수가 같다면, 사전 순서대로 기록된다.
 * 예를 들어, 주문서의 시작 부분은 다음과 같이 구성됩니다.
 *
 * "a"→"b"→"c"→"d"→"e"→"f"→...→"z"
 * →"aa"→"ab"→...→"az"→"ba"→...→"by"→"bz"→"ca"→...→"zz"
 * →"aaa"→"aab"→...→"aaz"→"aba"→...→"azz"→"baa"→...→"zzz"
 * →"aaaa"→...→"aazz"→"abaa"→...→"czzz"→"daaa"→...→"zzzz"
 * →"aaaaa"→...
 * 하지만 이 주문서에는 오래전 봉인된 저주받은 주문들이 숨겨져 있었고, 이를 악용하려는 자들을 막기 위해 마법사들이 몇몇 주문을 주문서에서 삭제했습니다. 당신은 삭제가 완료된 주문서에서 n번째 주문을 찾아내야 합니다.
 *
 * 예를 들어, 주문서에서 "d", "e", "bb", "aa", "ae" 5개의 주문을 지웠을 때, 주문서에서 30번째 주문을 찾으려고 합니다.
 *
 * 1~3번째 주문은 "a", "b", "c" 입니다.
 * "d"와 "e"는 삭제됐으므로 4~24번째 주문은 "f" ~ "z"입니다.
 * "aa"는 삭제됐으므로 25~27번째 주문은 "ab", "ac", "ad"입니다.
 * "ae"는 삭제됐으므로 28~30번째 주문은 "af", "ag", "ah"입니다.
 * 따라서 30번째 주문은 "ah"가 됩니다. 삭제된 주문 중 “bb”와 같이 n번째 주문보다 뒤에 위치해 있어서 n번째 주문을 찾는 데 영향을 주지 않는 주문도 존재할 수 있습니다.
 *
 * 정수 n과 삭제된 주문들을 담은 1차원 문자열 배열 bans가 매개변수로 주어질 때, 삭제가 완료된 주문서의 n번째 주문을 return 하도록 solution 함수를 완성해 주세요.
 *
 * 제한사항
 * 1 ≤ n ≤ 1015
 * 1 ≤ bans의 길이 ≤ 300,000
 * bans의 원소는 알파벳 소문자로만 이루어진 길이가 1 이상 11 이하인 문자열입니다.
 * bans의 원소는 중복되지 않습니다.
 * 입출력 예
 * n	bans	result
 * 30	["d", "e", "bb", "aa", "ae"]	"ah"
 * 7388	["gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"]	"jxk"
 * 테스트 케이스 구성 안내
 * 아래는 테스트 케이스 구성을 나타냅니다. 각 그룹 내의 테스트 케이스를 모두 통과하면 해당 그룹에 할당된 점수를 획득할 수 있습니다.
 *
 * 그룹	총점	추가 제한 사항
 * #1	15%	n ≤ 1,000, bans의 길이 ≤ 100
 * #2	15%	n ≤ 1,000,000
 * #3	70%	추가 제한 사항 없음
 * 입출력 예 설명
 * 입출력 예 #1
 *
 * 문제 예시와 같습니다.
 *
 * 입출력 예 #2
 *
 * 주어진 주문을 지운 후 주문서의 7,388 번째 주문은 "jxk"입니다.
 * 따라서 "jxk"를 return 합니다.
 */
public class boj_389481 {
    public String solution(long n, String[] bans) {
        String answer = "";
        long[] banLocations = new long[bans.length];

        for(int i=0; i<bans.length; i++){
            banLocations[i] = findLocation(bans[i]);
        }

        //잘못된 연산 방지를 위한 정렬
        Arrays.sort(banLocations);

        //정렬된 위치를 기준으로 n을 증가시킴
        for(long loc : banLocations){
            if(loc <= n) {
                n++;
            } else {
                break;
            }
        }

        return findString(n);
    }

    static long findLocation(String alphabets){
        int length = alphabets.length();
        long n = 0;

        long power = 1; // 26^0
        for(int i=1; i<length; i++){
            power *= 26;
            n += power;
        }

        char[] chars = alphabets.toCharArray();
        long adds = 1; // 1-indexed

        power = 1; // 26^0 (수동으로 26의 거듭제곱을 계산)

        for(int j=0; j<chars.length; j++){
            int charVal = (chars[chars.length-1-j] - 'a');

            adds += (long) charVal * power;

            power *= 26; // 다음 자리수
        }

        return n + adds;
    }

    static String findString(long n) {
        int length = 1;
        long countPerLength = 26;

        //길이 찾기
        while (n > countPerLength) {
            n -= countPerLength;
            countPerLength *= 26;
            length++;
        }

        //n을 1-indexed -> 0-indexed로 변환
        //("a" 또는 "aa" 등 그룹의 첫 단어) -> 0으로 변환
        n--;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            //숫자를 문자로 변환
            sb.append((char) ('a' + (n % 26)));
            n /= 26;
        }

        //낮은 자리수부터 만들어져서 뒤집기
        return sb.reverse().toString();
    }
}
