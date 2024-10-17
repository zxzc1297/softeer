package boj.sort;
import java.util.*;

/**
 * 문제
 * 세계적인 도둑 상덕이는 보석점을 털기로 결심했다.
 *
 * 상덕이가 털 보석점에는 보석이 총 N개 있다. 각 보석은 무게 Mi와 가격 Vi를 가지고 있다. 상덕이는 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 Ci이다. 가방에는 최대 한 개의 보석만 넣을 수 있다.
 *
 * 상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N과 K가 주어진다. (1 ≤ N, K ≤ 300,000)
 *
 * 다음 N개 줄에는 각 보석의 정보 Mi와 Vi가 주어진다. (0 ≤ Mi, Vi ≤ 1,000,000)
 *
 * 다음 K개 줄에는 가방에 담을 수 있는 최대 무게 Ci가 주어진다. (1 ≤ Ci ≤ 100,000,000)
 *
 * 모든 숫자는 양의 정수이다.
 *
 * 출력
 * 첫째 줄에 상덕이가 훔칠 수 있는 보석 가격의 합의 최댓값을 출력한다.
 *
 * 예제 입력 1
 * 2 1
 * 5 10
 * 100 100
 * 11
 * 예제 출력 1
 * 10
 * 예제 입력 2
 * 3 2
 * 1 65
 * 5 23
 * 2 99
 * 10
 * 2
 * 예제 출력 2
 * 164
 */
public class boj_1202 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        ArrayList<int[]> arr = new ArrayList<>();

        for(int i=0; i<n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();

            arr.add(new int[]{a,b});
        }

        //우선 무게순으로 정렬
        Collections.sort(arr, (a,b) -> {
            if(a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        int[] bag = new int[k];

        for(int i=0; i<k; i++){
            bag[i] = sc.nextInt();
        }

        //가방 무게 작은순으로 정렬
        Arrays.sort(bag);
        long answer = 0;
        int tmpIdx = 0;
        // 우선순위 큐 (최대 힙) - 가장 비싼 보석부터 꺼내기 위해
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<k; i++){
            int maxBag = bag[i];

            //우선순위 큐에 담을 수 있는 보석들 전부 담음
            while(tmpIdx < n && arr.get(tmpIdx)[0] <= maxBag){
                pq.offer(arr.get(tmpIdx)[1]);
                tmpIdx++;
            }

            // 큐에서 가장 비싼 보석을 선택
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        System.out.print(answer);
    }
}
