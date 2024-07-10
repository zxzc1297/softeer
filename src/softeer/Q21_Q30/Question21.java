package softeer.Q21_Q30;
import java.util.*;

/**
 * 회사에는 N개의 회의실이 있다. 수많은 팀이 모여 토론하고 업무를 처리하기 위해서는 회의실이 필수적이다.
 *
 *
 *
 * 내부망에 아주 간단한 회의실 예약 시스템이 있지만 편의성이 매우 떨어진다. 단순히 예약된 회의의 목록만 표시되기 때문에, 방 별로 비어 있는 시간이 언제인지를 확인하기가 힘든 것이다. 당신은 이를 직접 해결해 보기로 마음 먹었다.
 *
 *
 *
 * 회의실 이용 규칙은 다음과 같다:
 *
 *
 *
 * - 회의실은 9시부터 18시까지만 사용 가능하다. 모든 회의의 시간은 이 안에 완전히 포함되어야 한다.
 *
 * - 회의는 정확히 한 회의실을 연속한 일정 시간 동안만 점유한다. 즉 각 회의는 (회의실, 시작 시각, 종료 시각)의 정보로 나타낼 수 있다.
 *
 * - 회의의 시작과 종료 시각은 시(時, hour) 단위로만 설정 가능하다. 같은 회의실을 사용하는 회의 시간은 서로 겹칠 수 없다. 여기서 겹친다는 것은, 두 회의 모두에 포함되는 시간이 1시간 이상 존재한다는 것을 의미한다. 예를 들어, 10시-12시의 회의와 11시-13시의 회의는 겹치는데, 11시-12시의 시간이 두 회의 모두에 포함되기 때문이다.
 *
 * - 한 회의가 끝나는 시각에, 같은 회의실에서 다른 회의가 시작하는 것은 허용된다. 이 경우 두 회의가 겹치지 않기 때문이다.
 *
 * - 길이가 0인 회의, 즉 시작 시각과 종료 시각이 동일한 회의는 예약된 바 없으며, 새롭게 잡을 수도 없다.
 *
 *
 *
 * 이미 예약된 M개의 회의에 대한 정보가 주어지면, 회의실별로 비어 있는 시간대를 정리해 출력하는 프로그램을 작성해 보자. 구체적인 형식은 아래를 참고하시오.
 *
 * 제약조건
 * 1 ≤ N ≤ 50
 *
 * 1 ≤ M ≤ 100
 *
 * 회의실의 이름은 영문 알파벳 소문자로만 이루어져 있으며 길이는 1 이상 10 이하이다.
 *
 * 주어지는 모든 시각은 9 이상 18 이하이다.
 *
 * 회의의 시작 시각은 회의의 종료 시각을 1시간 이상 앞선다.
 *
 * 입력형식
 * 첫째 줄에 회의실의 수와 예약된 회의의 수를 나타내는 정수 N과 M이 공백을 사이에 두고 주어진다.
 *
 * 이어 N개의 줄에는 각 회의실의 이름이 주어진다.
 *
 * 이어 M개의 줄에는 각 회의가 배정된 회의실의 이름 r과 시작 시각 s, 그리고 종료 시각 t가 공백을 사이에 두고 주어진다.
 *
 * 출력형식
 * 각 회의실에 대한 정보를 회의실 이름의 오름차순으로 출력한다.
 *
 *
 *
 * 각 회의실에 대한 정보는 다음과 같다.
 *
 * 첫째 줄에는 { Room 회의실이름: } (중괄호 제외)을 출력한다.
 *
 * 둘째 줄에는 예약가능 시간을 출력한다.
 *
 * - 예약 가능한 시간대의 개수를 n이라고 할 때, { n available: } (중괄호 제외)을 출력하고, 뒤따른 n개의 줄에 예약 가능한 시간대를 { 09-18 } (하이픈 한개, 중괄호 제외)형태로 출력해야 한다. 한 자리 수의 경우 앞에 0을 붙여 두 자리 수로 만들어야 함에 유의하라.
 *
 * - 예약 가능한 시간이 없다면, Not available을 출력한다.
 *
 *
 *
 * 각 회의실에 대한 정보 사이에는 ----- (하이픈 다섯 개)로 구분선이 출력되어야 한다.
 */
public class Question21 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();
        String[] roomNames = new String[length];
        Map<String, Integer> rooms = new HashMap<>();
        ArrayList<Integer>[] times = new ArrayList[length];
        ArrayList<String>[] availables = new ArrayList[length];
        int reserv = sc.nextInt();

        for(int i=0; i<length; i++){
            String key = sc.next();
            roomNames[i] = key;
            rooms.put(key, i);
            times[i] = new ArrayList<>();
            availables[i] = new ArrayList<>();
        }
        Arrays.sort(roomNames);
        for(int j=0; j<reserv; j++){
            String key = sc.next();
            int idx = rooms.get(key);
            int start = sc.nextInt();
            int end = sc.nextInt();

            for(int k=start; k<end; k++){
                times[idx].add(k);
            }
        }

        for(int k=0; k<times.length; k++){
            Collections.sort(times[k]);
            // System.out.println(times[k].toString());
            int start = 0;
            int end = 0;
            for(int l=9; l<18; l++){

                if(!times[k].contains(l)){
                    if(l == 17){
                        if(start == 0){
                            start = 17;
                        }
                        String str = start<10 ? "0" + Integer.toString(start) : Integer.toString(start);
                        String avail = str + "-" + Integer.toString(18);
                        availables[k].add(avail);
                    } else if(start == 0){
                        start = l;
                    }
                } else {
                    if(start != 0){
                        end = l;
                        String str = start<10 ? "0" + Integer.toString(start) : Integer.toString(start);
                        String endt = Integer.toString(end);
                        String avail = str + "-" + endt;
                        availables[k].add(avail);
                        start = 0;
                    }
                }
            }
        }
        for (int n=0; n<roomNames.length; n++) {
            String name = roomNames[n];
            int roomIdx = rooms.get(name);
            System.out.println("Room " + name + ":");
            if(availables[roomIdx].size() == 0){
                System.out.println("Not available");
                if(n<length-1){
                    System.out.println("-----");
                }
            } else {
                int tempLength = availables[roomIdx].size();
                System.out.println(Integer.toString(tempLength) + " available:");

                for(int m=0; m<tempLength; m++){
                    System.out.println(availables[roomIdx].get(m));
                }

                if(n<length-1){
                    System.out.println("-----");
                }
            }
        }
        sc.close();
    }

}
