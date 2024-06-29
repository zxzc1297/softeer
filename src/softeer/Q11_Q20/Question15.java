package softeer.Q11_Q20;
import java.util.*;
/**
 * 현대자동차에서는 부드럽고 빠른 변속이 가능한 8단 습식 DCT 변속기를 개발하여 N라인 고성능차에 적용하였다.
 * 관련하여 SW 엔지니어인 당신에게 연속적으로 변속이 가능한지 점검할 수 있는 프로그램을 만들라는 임무가 내려왔다.

 * 당신은 변속기가 1단에서 8단으로 연속적으로 변속을 한다면 ascending,
 * 8단에서 1단으로 연속적으로 변속한다면 descending, 둘다 아니라면 mixed 라고 정의했다.

 * 변속한 순서가 주어졌을 때 이것이 ascending인지, descending인지,
 * 아니면 mixed인지 출력하는 프로그램을 작성하시오.
 *
 * 제약조건
 * 주어지는 숫자는 문제 설명에서 설명한 변속 정도이며, 1부터 8까지 숫자가 한번씩 등장한다.
 *
 * 입력형식
 * 첫째 줄에 8개 숫자가 주어진다.
 *
 * 출력형식
 * 첫째 줄에 ascending, descending, mixed 중 하나를 출력한다.
 *
 * 입력예제1
 * 1 2 3 4 5 6 7 8
 * 출력예제1
 * ascending
 */
public class Question15 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[8];
        int i = 0;
        String answer = "";

        while(sc.hasNext()){
            array[i++] = sc.nextInt();
        }

        for(int j=1; j<8; j++){
            if(array[j] - array[j-1] != 1 && array[j] - array[j-1] != -1){
                answer = "mixed";
            }
        }

        if(answer.equals("")){
            if(array[0] == 1){
                answer = "ascending";
            }else{
                answer = "descending";
            }
        }

        System.out.print(answer);
        sc.close();
    }
}
