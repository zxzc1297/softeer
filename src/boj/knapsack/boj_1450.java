package boj.knapsack;
import java.util.*;
import java.io.*;

/**
 * 문제
 * 세준이는 N개의 물건을 가지고 있고, 최대 C만큼의 무게를 넣을 수 있는 가방을 하나 가지고 있다.
 *
 * N개의 물건을 가방에 넣는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에 N과 C가 주어진다. N은 30보다 작거나 같은 자연수, C는 109보다 작거나 같은 음이 아닌 정수이다. 둘째 줄에 물건의 무게가 주어진다. 무게도 109보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 가방에 넣는 방법의 수를 출력한다.
 *
 * 예제 입력 1
 * 2 1
 * 1 1
 * 예제 출력 1
 * 3
 * 예제 입력 2
 * 1 1
 * 1
 * 예제 출력 2
 * 2
 * 예제 입력 3
 * 1 2
 * 1
 * 예제 출력 3
 * 2
 */
public class boj_1450 {
    static int N, C;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ArrayList<Integer> weight1 = new ArrayList<>();
        ArrayList<Integer> weight2 = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            if(i<N/2)
                weight1.add(Integer.parseInt(st.nextToken()));
            else
                weight2.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> sum1 = new ArrayList<>();
        ArrayList<Integer> sum2 = new ArrayList<>();

        dfs(0, 0, weight1, sum1);
        dfs(0, 0, weight2, sum2);

        int answer = 0;
        Collections.sort(sum2);
        for(int j=0; j<sum1.size(); j++){
            int searchValue = C - sum1.get(j);
            answer += binarySearch(sum2, searchValue) + 1;
        }

        System.out.println(answer);
        br.close();
    }

    public static void dfs(int idx, int sum, ArrayList<Integer> weight, ArrayList<Integer> result){
        if(sum>C) return;
        if(idx == weight.size()){
            result.add(sum);
            return;
        }

        dfs(idx+1, sum + weight.get(idx), weight, result);
        dfs(idx+1, sum, weight, result);
    }

    public static int binarySearch(ArrayList<Integer> sum, int target){
        int left = 0;
        int right = sum.size() - 1;
        int mid, value = -1;

        while(left <= right){
            mid = (left + right) / 2;
            if(sum.get(mid) <= target){
                value = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return value;
    }
}
