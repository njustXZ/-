package com.njustxz.hook;

import com.njustxz.HelloMonitor;

public class Solution {
    public static void main(String[] args) {
        HelloMonitor hm = new HelloMonitor();
        System.out.println(hm.getClass().getClassLoader());
    }
}
