package softeer.Q21_Q30;
import java.util.*;

/**
 * 현대자동차에서 근무하고 있는 로하는 주어진 작업 슬롯 하나로 N개의 자동차를 생산하는 공정을 계획하려 한다. 각 i번째 자동차는 생산하기 위해 si​단계의 생산 프로세스를 거쳐야 한다. 작업 슬롯은 수직선상에서 [0,1)로 표현할 수 있으며, i번째 자동차 생산이 진행 중인 경우 이 중 si​1​ 크기의 반열린 구간을 차지한다. 0초부터 시작해서 1초마다 다음과 같은 일이 순서대로 일어난다.
 *
 * 먼저 할당된 생산 프로세스부터, 작업 슬롯의 모든 생산 프로세스들은 자신의 크기만큼 뒤로 움직인다. 즉, 작업 슬롯의 전체 구간을 [0,1)로 표현했을 때, i번째 생산 프로세스가 [si​j−1​,si​j​)의 구간을 차지하고 있었다면, 이동 후 [si​j​,si​j+1​)의 구간을 차지하게 된다. 단, 이동 시 다른 생산 프로세스가 차지하는 구간과 겹치게 되는 경우에는 움직이지 않는다. 슬롯을 통과하여 [0,1) 밖으로 나온 경우 이 생산 프로세스는 완료된 것이며, 더 이상 공간을 차지하지 않는다.
 * 로하는 아직 할당하지 않은 생산 프로세스 중 하나를 골라 슬롯에 할당하거나 할당하지 않을 수 있다. i번째 생산 프로세스를 할당할 경우 슬롯의 [0,si​1​) 부분에 생산 프로세스를 할당한다. 단, 다른 생산 프로세스가 차지하고 있는 구간과 겹치는 경우 할당할 수 없다.
 * 모든 자동차가 작업 슬롯을 거쳐 생산될 때까지 걸리는 최소 시간이 몇 초인지 구하여라.
 *
 * 노트:
 *
 * 반열린구간 [a,b) 는 a 이상 b 미만의 모든 수를 포함하는 구간이다.
 *
 * 제약조건
 * 1≤N≤200000
 *
 * 1≤si​≤200000
 *
 * 입력형식
 * 첫 번째 줄에 생산해야 하는 자동차의 개수 N이 주어진다.
 *
 * 두 번째 줄에 N개의 자동차의 생산 프로세스에 대한 각각의 단계 수 s1​,s2​,…,sN​가 공백으로 구분되어 주어진다.
 *
 * 출력형식
 * 첫 번째 줄에, 모든 자동차가 작업 슬롯을 거쳐 끝날 때까지 걸리는 최소 시간을 초 단위로 출력한다.
 *
 * 입력예제1
 *
 * 4
 * 5 7 8 11
 *
 * 출력예제1
 *
 * 14
 *
 * 입력예제2
 *
 * 1
 * 200000
 *
 * 출력예제2
 *
 * 200000
 */
public class Question27 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cars = new int[n];

        //공정받기
        for(int i=0; i<n; i++){
            cars[i] = sc.nextInt();
        }

        //공정을 가장 작은 걸로 정렬
        Arrays.sort(cars);

        //정렬을 하게 된다면 공정은 사실상 순서대로 들어가게됨
        //2 3 4 라고 가정한다면 1/2 1/3 1/4 이기 때문에 초마다 겹치지 않고 들어갈 수 있고
        //그렇게 된다면 마지막 공정이 걸리는 시간 + 마지막 공정이 들어가는 시간으로 구할 수 있음
        //따라서 배열 길이 - 1 + 마지막 공정 시간 을 출력하면 됨

        System.out.print(n-1 + cars[n-1]);
        sc.close();
    }

    /** 시도하던 방법
     * public static void main(String[] args) {
     *         Scanner sc = new Scanner(System.in);
     *         int n = sc.nextInt();
     *         ArrayList<Integer> cars = new ArrayList<>();
     *         Queue<int[]> q = new LinkedList<>();
     *
     *         for(int i=0; i<n; i++){
     *             int tmp = sc.nextInt();
     *             cars.add(tmp);
     *         }
     *
     *         Collections.sort(cars);
     *
     *         //초기값 세팅 가장 시간이긴 짧은 공정을 넣으면 겹치는 구간이 하루가 지나면 더 적게 할당됨
     *         int totalSec = 0;
     *         q.offer(new int[]{cars.get(0),0});
     *         cars.remove(0);
     *         while(!q.isEmpty()){
     *             Deque<int[]> dq = new ArrayDeque<>();
     *             totalSec++;
     *
     *             //원래 있던 큐에서 다음날 큐에 전부 넣기
     *             while(!q.isEmpty){
     *                 int[] tmp = q.poll();
     *
     *                 //dq가 있을 때
     *                 if(dq.size()>0){
     *                     //1초후의 큐 생성
     *                     int[] last = dq.peekLast();
     *
     *                     double forProcess = (double) last[1] / last[0];
     *                     double tmpProcess = (double) tmp[1]+1 / tmp[0];
     *
     *                     //선행 프로세스와 겹치는지 안겹치는지 확인
     *                     if(forProcess>tmpProcess){
     *                         tmp[1] += 1;
     *                     }
     *                     dq.add(tmp);
     *
     *                 } // dq에 아무것도 없을 때
     *                 else {
     *                     tmp[1] += 1;
     *                     dq.add(tmp);
     *                 }
     *             }
     *
     *             //정렬되어 있는 배열에 가장 앞 순번의 진행도를 체크하고 확인
     *
     *             int[] last = dq.peekLast();
     *             int[] put = cars.get(0);
     *
     *             double forProcess = (double) last[1] / last[0];
     *             double tmpProcess = (double) put[1]+1 / put[0];
     *
     *             if(forProcess>tmpProcess){
     *                 dq.add(tmp);
     *             }
     *         }
     *
     *     }
     */
}
