package boj.implementation;
import java.util.*;

/**
 * 문제
 * 소현이는 친구들과 함께 볼링을 치러 볼링장에 갔다. 그런데 볼링장의 시스템 오류로 인해 점수판에 점수가 집계 되지 않는 문제가 있었다. 밖이 너무 추운 나머지 소현이와 친구들은 그냥 치기로 하였고 1게임이 끝났지만 각자 점수가 얼마나 되는지를 계산하지 못하고 있다. 소현이와 친구들을 위해 볼링 점수를 계산해주는 프로그램을 작성해 보자.
 *
 * 볼링 규칙
 *
 * 1게임은 총 10프레임으로 구성되어 있다.
 * 각 프레임마다 볼링핀 10개를 세워두고 공으로 쓰러뜨리는 것이며 기본적으로 볼링핀 1개당 1점이다.
 * 각 프레임마다 2번의 기회가 주어지며 첫 번째 기회에 10개의 핀을 모두 쓰러뜨리는 것을 스트라이크(S)라고 한다.
 * 두 번째 기회까지 사용하여 10개의 핀을 쓰러뜨리는 것을 스페어(P)라고 한다.
 * 스트라이크를 치면 다음 두 번의 기회동안 쓰러뜨린 볼링핀의 개수만큼 추가점수를  얻게 된다.
 * 10프레임을 제외한 프레임에서 스트라이크를 치면 해당 프레임의 두 번째 기회는 사라진다.
 * 스페어를 치면 다음 한번의 기회동안 쓰러뜨린 볼링핀의 개수만큼 추가점수를 얻게 된다.
 * 마지막 10번째 프레임에서 스트라이크를 칠 경우 두번의 보너스 기회가 제공된다. 이때 두번의 보너스 기회동안 추가점수는 존재하지 않는다.
 * 마지막 10번째 프레임에서 스페어를 칠 경우 한번의 보너스 기회가 제공된다. 이때 한번의 보너스 기회동안 추가점수는 존재하지 않는다.
 * 입력
 * 첫째 줄에 각 기회마다 소현이가 쓰러뜨린 볼링핀의 개수가 공백없이 주어진다. 이때 스트라이크는 S, 스페어는 P, 핀을 하나도 못 쓰러뜨린 것은 -으로 주어진다.
 *
 * 출력
 * 첫째 줄에 소현이의 점수를 출력한다.
 *
 * 예제 입력 1
 * 9-8P72S9P-9S-P9-SS8
 * 예제 출력 1
 * 150
 * 예제 입력 2
 * SSSSSSSSSSSS
 * 예제 출력 2
 * 300
 */
public class boj_17215 {
    static int[] scores;
    static char[] tries;
    static int[] last = new int[10];
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        tries = new char[str.length()];
        scores = new int[str.length()];

        //시도별 점수 기록
        for(int i=0; i<str.length(); i++){
            tries[i] = str.charAt(i);
            score(i,str.charAt(i));
        }

        int answer = 0;

        int idx = 0;
        //프레임별 점수 계산하기
        for(int i=0; i<10; i++){
            //스트라이크일 때
            if(tries[idx] == 'S'){
                last[i] = scores[idx++];
            } //스트라이크가 아닐 때
            else {
                last[i] = scores[idx] + scores[idx+1];
                idx = idx + 2;
            }
        }

        for(int ans : last){
            answer += ans;
        }

        //결과 출력
        System.out.print(answer);
        sc.close();
    }

    //점수
    public static void score(int idx, char now){
        int s = 0;

        //현재 시도 점수 계산
        if(now == 'S') s = 10;
        else if(now == 'P') s = tries[idx-1] == '-' ? 10 : 10 - (tries[idx-1] - '0');
        else if(now == '-') s = 0;
        else s = now - '0';

        //점수 기록
        scores[idx] = s;

        //이전 기록들에 보너스 점수 계산
        if(idx>1){
            if(tries[idx-2] == 'S') scores[idx-2] += s;
        }

        if(idx>0){
            if(tries[idx-1] == 'S' || tries[idx-1] == 'P') scores[idx-1] += s;
        }
    }
}
