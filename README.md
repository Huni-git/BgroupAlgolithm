# BgroupAlgolithm
# 과제 : 분할 정복 방법으로 최근접 점의 쌍 찾기

## 1. 최근접 점의 쌍 문제란?

최근접 점의 쌍을 푸는 데는 많은 문제를 푸는 알고리즘 및 코드가 존재한다. 그 중에서도 가장 대표적인 방법은 일명 **노가다**라고도 부르는 ~~두 점 사이의 거리를 모두 계산하는 것이다.~~  하지만 이 방법을 사용할 경우 nC2 = n(n-1)/2 = O(n2)의 시간복잡도로써 굉장한 오랜 시간을 투자해야 한다.

그래서 우리는 지난 수업 시간에 배운 분할 정복을 이용해 문제를 풀어볼 것이다. n개의 점을 분할하여 분할한 부분에서 최소 거리를 찾고 정복 즉 병합을 해서 문제를 풀어주는 과정이다. 자세한 설계 과정은 3번에서 확인해보도록 하자.
![225px-Closest_pair_of_points svg](https://user-images.githubusercontent.com/80510945/113153179-cb9dac80-9271-11eb-86a9-c91febeb69bc.png)



## 2. 분할 정복 알고리즘이란?

![image](https://user-images.githubusercontent.com/80517119/113153449-128ba200-9272-11eb-8be0-46e4ce824480.png)

분할 정복의 기본 매커니즘은 큰 문제를 여러개로 쪼갠뒤 다시 합쳐 정답을 구하는 방식입니다. 
이를 단계로 나눈다면 크게 3가지로 나뉩니다. 
문제의 분할(Divide), 정복(Conquer), 마지막으로 알고리즘의 정답을 구하기 위한 병합(Combine) 과정입니다.

부분문제와 부분해
- 분할된 입력에 대한 문제를 부분문제라고 합니다.
- 부분문제의 해를 부분해라고 합니다. 
- 부분문제는 더 이상 분할할 수 없을 때까지 계속 분할합니다.

정복 과정
- 부분해를 찾아야 합니다.
- 일반적으로 부분 문제들의 해를 취합하여 보다 큰 부분 문제의 해를 구합니다.


## 3. 설계과정

1. 우선 몇 개의 좌표를 입력할 지 설정 후,  점의 좌표 배열을 직접 입력한다.
2. 입력 받은 n개의 점을 1/2로 분할한다.
3. 분할한 면에 가장 짧은 거리를 가진 점의 쌍을 찾는다.
4. ~~두개의 면에서  나온 거리를 merge 최근접쌍을 구해준다.~~
5. 두개의 면에서 나온 거리 중 최소 거리를 구해준다. 
   * **분할한 면끼리에서 생길 수 있는 최소 거리를 고려한다.**
6. 두개의 면에서 나온 거리 중 최소 거리와 분할한 면 사이에서 생긴 최소 거리를 비교한다.
7. 부분해를 모두 취합 후, 최소 거리를 가진 최근접 점 좌표를 찾아낸다.

![알고리즘](https://user-images.githubusercontent.com/80510945/113150609-37cae100-926f-11eb-9ef7-6fdea6db7e34.png)


## 4. 자바 코드
```java
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




```

## 5. 결과


## 6. 참조 및 이미지

[위키백과(1번째사진)]([최근접 점쌍 문제 - 위키백과, 우리 모두의 백과사전 (wikipedia.org)](https://ko.wikipedia.org/wiki/최근접_점쌍_문제))

[분할 정복(2번째사진)](https://www.javatpoint.com/divide-and-conquer-introduction)

[IT 블로그(3번째 사진)]([[Algorithm\] 최근접 점의 쌍 찾기 (분할정복) | NoName, IT blog (dudri63.github.io)](https://dudri63.github.io/2019/01/19/algo8/))
