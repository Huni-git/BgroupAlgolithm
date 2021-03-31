# BgroupAlgolithm
# 과제:분할 정복 방법으로 최근접 점의 쌍 찾기

## 1. 최근접 점의 쌍 문제란?

최근접 점의 쌍을 푸는 데는 많은 문제를 푸는 알고리즘 및 코드가 존재한다. 그 중에서도 가장 대표적인 방법은 일명 **노가다**라고도 부르는 ~~두 점 사이의 거리를 모두 계산하는 것이다.~~  하지만 이 방법을 사용할 경우 nC2 = n(n-1)/2 = O(n2)의 시간복잡도로써 굉장한 오랜 시간을 투자해야 한다.

그래서 우리는 지난 수업 시간에 배운 분할 정복을 이용해 문제를 풀어볼 것이다. n개의 점을 분할하여 분할한 부분에서 최소 거리를 찾고 정복 즉 병합을 해서 문제를 풀어주는 과정이다. 자세한 설계 과정은 [3번에서 확인해보도록 하자.](#3. 코드 실행 전 만든 설계 과정)


## 2. 분할 정복 알고리즘이란?
분할 정복의 기본 매커니즘은 큰 문제를 여러개로 쪼갠뒤 다시 합쳐 정답을 구하는 방식입니다. 이를 단계로 나눈다면 크게 3가지로 나뉩니다. 
첫번째 문제의 분할(Divide), 그리도 두번째 정복(Conquer), 마지막으로 알고리즘의 정답을 구하기 위한 병합(Combine)과정입니다.

부분문제와 부분해
- 분할된 입력에 대한 문제를 부분문제라고 합니다.
- 부분문제의 해를 부분해라고 합니다. 
- 부분문제는 더 이상 분할할 수 없을 때까지 계속 분할합니다.

정복 과정
- 부분해를 찾아야 합니다.
- 정복하는 방법은 문제에 따라 다릅니다.
- 일반적으로 부분 문제들의 해를 취합하여 보다 큰 부분 문제의 해를 구합니다.


## 3. 코드 실행 전 만든 설계 과정 #3. 코드 실행 전 만든 설계 과정

1. 우선 랜덤에  점 배열을 입력 받는다.
2. 입력 받은 n개의 점을 1/2로 분할한다.
3. 분할한 면에 가장 짧은 거리를 가진 점의 쌍을 찾는다.
4. ~~두개의 면에서  나온 거리를 merge 최근접쌍을 구해준다.~~
5. 두개의 면에서 나온 거리 중 최소 거리를 구해준다. 
   * **분할한 면끼리에서 생길 수 있는 최소 거리를 고려한다.**
6. 두개의 면에서 나온 거리 중 최소 거리와 분할한 면 사이에서 생긴 최소 거리를 비교한다.
7. 부분해를 모두 취합 후, 최소 거리를 가진 최근접 점 좌표를 찾아낸다.

![알고리즘](https://user-images.githubusercontent.com/80510945/113150609-37cae100-926f-11eb-9ef7-6fdea6db7e34.png)





