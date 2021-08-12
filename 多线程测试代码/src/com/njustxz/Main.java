package com.njustxz;

import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int x = input.nextInt();
        int y = input.nextInt();
        if(2*x > n || 2*y<n){
            System.out.println("-1");
            return;
        }
        ArrayList<Integer> score = new ArrayList<>();
        for(int i=0;i<n;i++){
            score.add(input.nextInt());
        }
        Collections.sort(score);
        if(2*x==n || 2*y==n){
            if(score.get(n/2-1)==score.get(n/2)){
                System.out.println("-1");
            }else{
                System.out.println(score.get(n/2-1));
            }
            return;
        }
        if(x==0){
            System.out.println(score.get(0));
            return;
        }
        int tmp=score.get(x-1);
        int tmpIndex = score.lastIndexOf(x);
        if(tmpIndex>y){
            System.out.println("-1");
        }else{
            System.out.println(score.get(tmpIndex-1));
        }
    }
}
