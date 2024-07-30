package softeer.Q21_Q30;
import java.util.*;
public class Question24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        Queue<long[]> q = new LinkedList<>();

        //큐에 현재 순서 넣기
        for(long i=0; i<n; i++){
            long temp = sc.nextLong();
            q.offer(new long[]{i+1, temp});
        }


        while(q.size() > 1){
            Deque<long[]> nextDays = new ArrayDeque<>();

            while(!q.isEmpty()) {
                long[] temp = q.poll();
                long size = temp[1];
                if(!nextDays.isEmpty()){
                    long[] last = nextDays.peekLast();

                    if(last[1] <= temp[1]){
                        size = size + last[1];
                        nextDays.pollLast();
                    }
                }

                if(!q.isEmpty()){
                    long[] next = q.peek();
                    if(temp[1] >= next[1]){
                        size = size + next[1];
                        q.poll();
                    }
                }
                temp[1] = size;
                nextDays.add(temp);
            }
            q = nextDays;
        }
        long[] answer = q.poll();
        System.out.println(answer[1]);
        System.out.println(answer[0]);
    }

    public static void solution1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Queue<int[]> q = new LinkedList<>();

        //큐에 현재 순서 넣기
        for(int i=0; i<n; i++){
            int temp = sc.nextInt();
            q.offer(new int[]{i+1, temp});
        }

        //큐에서 빼면서 합칠지 안합칠지 여부 확인
        int[] last = new int[2];

        while(!q.isEmpty()){
            int[] temp = q.poll();
            //System.out.println(Arrays.toString(last));
            if(last[0] < 1){
                int[] peek = q.peek();

                if(temp[1] > peek[1]){
                    int[] temp2 = q.poll();
                    temp[1] = temp[1] + temp2[1];
                }
            } else {

            }
            if(last[1] < temp[1]){
                last[0] = temp[0];
                last[1] = last[1] + temp[1];
            } else {
                q.offer(last);
                int[] peek = q.peek();

                if(temp[1] > peek[1]){
                    int[] temp2 = q.poll();
                    temp[1] = temp[1] + temp2[1];
                }
                last = temp;
            }
        }

        System.out.println(last[1]);
        System.out.println(last[0]);
    }

    public static void solution2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Queue<int[]> q = new LinkedList<>();

        //큐에 현재 순서 넣기
        for(int i=0; i<n; i++){
            int temp = sc.nextInt();
            q.offer(new int[]{i+1, temp});
        }


        while(q.size() > 1){
            Queue<int[]> nextDays = new LinkedList<>();

            while(!q.isEmpty()) {
                int[] temp = q.poll();

                if(nextDays.size()>0){
                    int[] last = nextDays.peek();

                    if(last[1] <= temp[1]){
                        temp[1] = temp[1] + last[1];
                        nextDays.poll();
                        nextDays.offer(temp);
                        continue;
                    }
                }

                int[] next = q.peek();
                if(temp[1] >= next[1]){
                    temp[1] = temp[1] + next[1];
                    q.poll();
                }
                nextDays.offer(temp);
            }
            q = nextDays;
        }
        int[] answer = q.poll();
        System.out.println(answer[1]);
        System.out.println(answer[0]);
    }
}
