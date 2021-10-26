#include <iostream>

using namespace std;

void dfs(int n, int number, int cnt, int current);
int answer = 9;

int solution(int N, int number) {
    dfs(N, number, 0, 0);
    if(answer > 8) return -1;
    return answer;
}

void dfs(int n, int number, int cnt, int current){
    if(cnt>=9)
       return;
    if(current == number){
        if(answer > cnt) answer = cnt;
    }      
    int temp = 0;
    for(int i = 0; i < 9; i++){
        temp = temp*10 + n;
        dfs(n,number,cnt+1+i,current + temp);
        dfs(n,number,cnt+1+i,current - temp);
        dfs(n,number,cnt+1+i,current * temp);
        dfs(n,number,cnt+1+i,current / temp);
    }
}