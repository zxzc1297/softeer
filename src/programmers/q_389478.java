package programmers;

public class q_389478 {
    public int solution(int n, int w, int num) {
        int answer = 1;
        int cur = num;
        //내 위의 데이터가 뭔지 연속적으로 찾기 최대 횟수 넘으면 탈출
        while(cur <= n){
            int curRest = cur%w;

            //내 위의 번호는 w*2 - (내 나머지 *2 - 1)를 더하기 나머지가 0일땐 +1
            cur = curRest > 0 ? cur + (w*2 - (curRest*2 - 1)) : cur + 1;

            if(cur<=n) answer++;
        }

        return answer;
    }
}
