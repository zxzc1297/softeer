package softeer.Q1_Q10;

import java.util.Scanner;

/*
퍼즐을 좋아하는 하이비는 작년에 이어 올해에도 퍼즐과 관련된 문제를 내기로 했다.
이번에는 Indirect Indexing으로, 다음과 같은 방식을 따른다.
1. N개의 문자열 쌍 (S1,T1), (S2,T2),…, (SN,TN)이 주어진다. 각 쌍에 대해, Si의 길이와 Ti의 길이는 같다.

2.  Si에서 글자 x 또는 X가 등장하는 위치를 Pi라고 하자. 이 위치는 항상 유일하다.

3. 이때, Ti의 Pi번째 글자를 읽으면 된다. 단, 소문자는 대문자로 바꿔야 한다.

4. 예를 들어, Si가 Indexing이고 Ti가 Indirect라면 읽게 되는 글자는 R이 된다.

제약조건
1 ≤ N ≤ 500,000

입력되는 문자열의 길이 합은 1,000,000을 넘지 않으며, 모든 문자열은 영어 알파벳 대소문자 또는 숫자로만 이루어져 있다.

입력형식
첫 번째 줄에 문자열 쌍의 개수 N이 주어진다.

두 번째 줄부터 N개의 줄에 걸쳐, i+1번째 줄에는 쌍을 이루는 두 문자열 Si,Ti가 공백으로 구분되어 주어진다.

출력형식
첫 번째 줄에 N개의 문자열 쌍에 대해 읽게 되는 글자를 차례대로 붙여서 출력한다.

입력예제1
8
Exit A1in
Axis A0on
Exam Star
WKXM XHHV
maxB pyht
XBut Club
ATax Keep
ifXY doC2
출력예제1
10THHCPC
입력예제2
13
Fix Via
Axis Anna
Linux Ideas
Matrix Review
Maximum ToExist
Exercise Practice
GrandPrix ProjectsI
Extraction Assistance
ComplexUnit Contributor
GulfOfMexico JohnHamilton
Approximately AfricaAndAsia
InTheContextOf Internationals
TextAlignCenter LakeSpringfield
출력예제2
ANSWERISBLANK
 */
public class Question10 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while(sc.hasNext()){
            String text1 = sc.next();
            String text2 = sc.next();
            int idx = -1;
            for(int i=0; i<text1.length(); i++){
                if(text1.substring(i,i+1).toLowerCase().equals("x")){
                    idx = i;
                    break;
                }
            }
            sb.append(text2.substring(idx,idx+1).toUpperCase());
        }

        System.out.print(sb.toString());
        sc.close();
    }
}
