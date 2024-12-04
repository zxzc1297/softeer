package boj.bruteForce;
import java.util.*;

/**
 * 문제
 * 수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.
 *
 * 리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.
 *
 * 수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.
 *
 * 수빈이가 지금 보고 있는 채널은 100번이다.
 *
 * 입력
 * 첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다. 둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다. 고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 같은 버튼이 여러 번 주어지는 경우는 없다.
 *
 * 출력
 * 첫째 줄에 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력한다.
 *
 * 예제 입력 1
 * 5457
 * 3
 * 6 7 8
 * 예제 출력 1
 * 6
 * 예제 입력 2
 * 100
 * 5
 * 0 1 2 3 4
 * 예제 출력 2
 * 0
 * 예제 입력 3
 * 500000
 * 8
 * 0 2 3 4 6 7 8 9
 * 예제 출력 3
 * 11117
 * 예제 입력 4
 * 100
 * 3
 * 1 0 5
 * 예제 출력 4
 * 0
 * 예제 입력 5
 * 14124
 * 0
 * 예제 출력 5
 * 5
 */
public class boj_1107 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int targetChannel = scanner.nextInt(); // 이동하려는 채널 N
        int brokenCount = scanner.nextInt(); // 고장난 버튼 개수
        boolean[] broken = new boolean[10]; // 고장난 버튼 여부 체크

        // 고장난 버튼 입력
        if (brokenCount > 0) {
            for (int i = 0; i < brokenCount; i++) {
                int brokenButton = scanner.nextInt();
                broken[brokenButton] = true;
            }
        }

        int currentChannel = 100; // 시작 채널
        int minPressCount = Math.abs(targetChannel - currentChannel); // +,-만으로 이동하는 경우

        // 브루트포스: 가능한 모든 채널(0~999,999) 탐색
        for (int i = 0; i <= 999_999; i++) {
            String channel = String.valueOf(i);
            if (isPossible(channel, broken)) {
                // 숫자 버튼을 눌러서 i로 이동한 후 +,-로 targetChannel까지 이동
                int pressCount = channel.length() + Math.abs(i - targetChannel);
                minPressCount = Math.min(minPressCount, pressCount);
            }
        }

        System.out.println(minPressCount);
    }

    // 채널 번호가 고장난 버튼 없이 입력 가능한지 확인
    public static boolean isPossible(String channel, boolean[] broken) {
        for (char c : channel.toCharArray()) {
            if (broken[c - '0']) {
                return false; // 고장난 버튼이 포함되어 있으면 불가능
            }
        }
        return true;
    }
}
