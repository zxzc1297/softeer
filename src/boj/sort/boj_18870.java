package boj.sort;
import java.util.*;

/**
 * 문제
 * 수직선 위에 N개의 좌표 X1, X2, ..., XN이 있다. 이 좌표에 좌표 압축을 적용하려고 한다.
 *
 * Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표 Xj의 개수와 같아야 한다.
 *
 * X1, X2, ..., XN에 좌표 압축을 적용한 결과 X'1, X'2, ..., X'N를 출력해보자.
 *
 * 입력
 * 첫째 줄에 N이 주어진다.
 *
 * 둘째 줄에는 공백 한 칸으로 구분된 X1, X2, ..., XN이 주어진다.
 *
 * 출력
 * 첫째 줄에 X'1, X'2, ..., X'N을 공백 한 칸으로 구분해서 출력한다.
 *
 * 제한
 * 1 ≤ N ≤ 1,000,000
 * -109 ≤ Xi ≤ 109
 * 예제 입력 1
 * 5
 * 2 4 -10 4 -9
 * 예제 출력 1
 * 2 3 0 3 1
 * 예제 입력 2
 * 6
 * 1000 999 1000 999 1000 999
 * 예제 출력 2
 * 1 0 1 0 1 0
 */
public class boj_18870 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        int[] arrSorted = new int[n];

        //정렬할 배열까지 생성
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
            arrSorted[i] = arr[i];
        }

        //정렬
        Arrays.sort(arrSorted);

        //압축 정보를 담을 map 생성
        Map<Integer, Integer> map = new HashMap<>();

        //압축값 기록
        int cnt = 0;

        //압축값 map에 저장
        for(int i=0; i<n; i++){
            if(!map.containsKey(arrSorted[i])){
                map.put(arrSorted[i], cnt++);
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++){
            sb.append(map.get(arr[i])).append(" ");
        }

        System.out.println(sb.toString().trim());
        sc.close();
    }
}
