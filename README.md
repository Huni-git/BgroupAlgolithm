# BgroupAlgolithm
# 과제 : 분할 정복 방법으로 최근접 점의 쌍 찾기

## 1. 최근접 점의 쌍 문제란?

최근접 점의 쌍을 푸는 데는 많은 문제를 푸는 알고리즘 및 코드가 존재한다. 그 중에서도 가장 대표적인 방법은 일명 **노가다**라고도 부르는 ~~두 점 사이의 거리를 모두 계산하는 것이다.~~  하지만 이 방법을 사용할 경우 nC2 = n(n-1)/2 = O(n2)의 시간복잡도로써 굉장한 오랜 시간을 투자해야 한다.

그래서 우리는 지난 수업 시간에 배운 분할 정복을 이용해 문제를 풀어볼 것이다. n개의 점을 분할하여 분할한 부분에서 최소 거리를 찾고 정복 즉 병합을 해서 문제를 풀어주는 과정이다. 자세한 설계 과정은 [3번에서 확인해보도록 하자.](#link here)

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


## 3. 설계과정 (Link here)

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





```

## 5. 결과


## 6. 참조 및 이미지

[위키백과(1번째사진)]([최근접 점쌍 문제 - 위키백과, 우리 모두의 백과사전 (wikipedia.org)](https://ko.wikipedia.org/wiki/최근접_점쌍_문제))

[분할 정복(2번째사진)](https://www.javatpoint.com/divide-and-conquer-introduction)

[IT 블로그(3번째 사진)]([[Algorithm\] 최근접 점의 쌍 찾기 (분할정복) | NoName, IT blog (dudri63.github.io)](https://dudri63.github.io/2019/01/19/algo8/))
