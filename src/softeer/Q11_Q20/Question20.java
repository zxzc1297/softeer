package softeer.Q11_Q20;
import java.util.*;

/**
 * 현대차그룹에 다니는 당신은 전세계 유가 변동에 대해 실시간으로 파악하기 위해 사무실에 유가를 실시간으로 표시하는 전광판을 설치하였다. 전광판은 최대 다섯 자리의 자연수만을 표시할 수 있도록, 아래와 같이 육각형 모양의 전구 7×5=35개로 구성되어 있다.
 * 8자 모양의 전구 묶음은 0부터 9까지의 숫자를 표현할 수 있으며, 표현 방법은 아래와 같다. 아래 그림에서 전구가 켜졌으면 검정색, 꺼졌으면 옅은 회색으로 표현되었다.
 *
 *
 * 예를 들어, 전광판을 통해 9881를 표현하면 아래와 같다. 만의 자리 수가 없기 때문에, 만의 자리에 해당하는 전구들이 모두 꺼져 있음에 유의하라.
 *
 * 예를 들어, 전광판을 통해 10724를 표현하면 아래와 같다.

 * 각각의 전구에는 스위치가 달려 있다. 전구에 달려 있는 스위치를 누를 때, 그 전구가 켜져 있었다면 꺼지고, 그 전구가 꺼져 있었다면 켜진다.
 *
 *
 *
 * 지금 전광판에 자연수 A가 표시되어 있는데, 유가가 변동됨에 따라 전광판에 표시된 자연수를 B로 바꿔야 한다. 이러한 목표를 달성하기 위해 스위치를 최소 몇 번 눌러야 하는지를 구하는 프로그램을 작성하라.
 *
 * 제약조건
 * 하나의 입력에서 1개 이상 1000개 이하의 테스트 케이스를 해결해야 한다.
 *
 * A와 B는 한 자리 이상 다섯 자리 이하의 자연수이다.
 *
 * A와 B는 숫자 0으로 시작하지 않는다.
 *
 * A와 B는 서로 다르다.
 *
 * 입력형식
 * 첫 번째 줄에 해결할 테스트 케이스의 수 T가 주어진다.
 *
 * 다음 T개의 줄에는 한 줄에 테스트 케이스 하나씩이 주어진다. 각각의 줄에는 두 자연수 A와 B가 공백 하나를 사이로 두고 주어진다.
 *
 * 출력형식
 * 각각의 테스트 케이스마다 순서대로, 스위치를 눌러야 하는 최소 횟수를 한 줄에 하나씩 출력한다.
 *

 */
public class Question20 {
    public static void main(String[] args) {

        List<String> lights = new ArrayList<>();

        lights.add("1110111"); //0
        lights.add("1100000"); //1
        lights.add("1011101"); //2
        lights.add("1111100"); //3
        lights.add("1101010"); //4
        lights.add("0111110"); //5
        lights.add("0111111"); //6
        lights.add("1110010"); //7
        lights.add("1111111"); // 8
        lights.add("1111110"); //9
        lights.add("0000000"); //공백

        Scanner sc = new Scanner(System.in);

        int answerLength = sc.nextInt();
        int[] answers = new int[answerLength];

        for(int i=1; i<=answerLength; i++){
            int answer = 0;
            StringBuilder first = new StringBuilder(sc.next());
            StringBuilder second = new StringBuilder(sc.next());

            if(first.length() != 5){
                int length1 = first.length();
                for(int m=0; m < 5 - length1; m++){
                    first.insert(0, "b");
                }
            }

            if(second.length() != 5){
                int length2 = second.length();
                for(int j=0; j<5-length2; j++){
                    second.insert(0, "b");
                }
            }

            char[] firsts = first.toString().toCharArray();
            char[] seconds = second.toString().toCharArray();

            for(int k=0; k<5; k++){
                if(firsts[k] != seconds[k]){
                    int idx1 = firsts[k] != 'b' ? firsts[k] - '0' : 10;
                    int idx2 = seconds[k] != 'b' ? seconds[k] - '0' : 10;
                    char[] target1 = lights.get(idx1).toCharArray();
                    char[] target2 = lights.get(idx2).toCharArray();
                    for(int l=0; l<7; l++){
                        if(target1[l] != target2[l]){
                            answer++;
                        }
                    }
                }
            }
            System.out.println(answer);

        }

        sc.close();

    }
}
