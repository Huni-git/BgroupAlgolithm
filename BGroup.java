import java.util.*;

public class BGroup {
    // 두 점 사이의 거리 구하는 공식
    private double dist(int[][] P, int[][]Q, int s, int t) {
        // 배열 Q = 최근접 쌍을 저장할 배열, s = 처음 인덱스, t = 끝 인덱스
        double min = 0; // min = 최소거리
        int c = 0; // c에 최근접 쌍의 P배열의 x좌표 인덱스를 저장

        for (int a = s; a < t; a++) {
            double x = Math.pow(P[a][0] - P[a + 1][0], 2); // x = x좌표 사이의 거리의 제곱
            double y = Math.pow(P[a][1] - P[a + 1][1], 2); // y = y좌표 사이의 거리의 제곱
            double d = Math.sqrt(x + y); // d = 두 점 사이의 거리
            if (a == s || d < min) { // a가 최소이거나 d가 최소값을 가질 때
                min = d;             // min에 d 값을 갱신
                c = a;              // 최소값이 갱신될때 마다 인덱스를 c에 저장
            }
        }

        // Q배열에 최근접 점 쌍을 저장하는 과정
        for (int i = 0; i < 2; i++) {
            Q[i][0] = P[c][0];
            Q[i][1] = P[c][1];
            c++;
        }
        return min;  // 최종 최소거리값으로 리턴합니다.
    }

    private void Devide(int[][] P, int[][] Q, int s, int t) {
        if(t-s<=3) return; // [ 점의 개수가 3개 이하면 분할을 멈춘다 ]
        int r = (s+t)/2; // 중간 index를 구해주는 과정이다.
        Devide(P, Q, s, r); // [ 왼쪽을 재귀 호출해주는 과정 ]
        Devide(P, Q,r+1, t); // [ 오른쪽을 재귀 호출해주는 과정]
        conquer(P, Q, s, r, t); // merge 함수 호출
    }

    private void conquer (int[][] P, int[][] Q, int s, int t, int u){
        // 점들을 저장할 배열, 최근접쌍을 저장할 배열, 처음 인덱스, 중간 인덱스, 끝 인덱스

        int[][] LEFT = new int[2][2]; // 왼쪽영역의 최근접쌍을 저장
        int[][] RIGHT = new int[2][2]; // 오른쪽영역의 최근접쌍을 저장
        int[][] CENT = new int[2][2]; // 중간영역의 최근접쌍을 저장

        double l = dist(P, LEFT, s, t); // 왼쪽영역 쌍의 최소거리
        double r = dist(P, RIGHT, t+1, u); // 오른쪽영역 쌍의 최소거리

        double d = (l < r) ? l : r; // 두 영역 중 최근접쌍의 거리를 d로

        // 중간영역의 앞쪽을 설정
        for(int i = 0; i < P.length; i++) {
            if ((P[t][0] - d) <= P[i][0]) {
                s = i;
                break;
            }
        }
        // 중간영역의 뒤쪽을 설정
        for(int i = P.length-1; i >= 0; i--) {
            if ((P[t+1][0] + d) >= P[i][0]) {
                u = i;
                break;
            }
        }

        double c = dist(P, CENT, s, u); // 중간영역 쌍의 최소거리

        // 영역중 가장 최근접쌍을 배열 Q에 저장
        if ((l <= c) && (l <= r)) { // 왼쪽영역이 최소일때
            for (int i = 0; i < 2; i++) {
                Q[i][0] = LEFT[i][0];
                Q[i][1] = LEFT[i][1];
            }
        }

        else if ((c <= l) && (c <= r)) { // 중간영역이 최소일때
            for (int i = 0; i < 2; i++) {
                Q[i][0] = CENT[i][0];
                Q[i][1] = CENT[i][1];
            }
        }

        else if ((r <= l) && (r <= c)) { // 오른쪽영역이 최소일때
            for (int i = 0; i < 2; i++) {
                Q[i][0] = RIGHT[i][0];
                Q[i][1] = RIGHT[i][1];
            }
        }

    }

    public static void main(String[] args) {
        System.out.print("점의 개수를 입력해주세요:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 점의 개수 입력
        int[][] A = new int[n][2]; // 입력받은 수 만큼 배열 생성
        int[][] Solve = new int[2][2]; // 최근접쌍을 받을 배열 생성

        System.out.println("점의 좌표를 입력해주세요");
        for (int i = 0; i < n; i++) {
            A[i][0] = sc.nextInt(); // x값에 저장
            A[i][1] = sc.nextInt(); // y값에 저장
        }

        // 점들의 좌표를 내림차순으로 정렬
        Arrays.sort(A, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[0] == o1[0])
                    return o2[1] - o1[1];
                else
                    return o2[0] - o1[0];
            }
        });

        // 점들의 좌표를 출력
        for (int i = 0; i < A.length; i++) {
            System.out.print("(" + A[i][0] + "," + A[i][1] + ")");
            System.out.println();
        }

        BGroup closet = new BGroup();
        closet.Devide(A, Solve, 0, A.length-1);

        // 작성된 코드 실행 후 정답을 내보내는 print문 실행
        System.out.print("두 점의 좌표 : ");
        for (int i = 0; i < 2; i++)
            System.out.print("(" + Solve[i][0] + "," + Solve[i][1] + ") ");
    }
}