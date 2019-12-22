package fr.ul.cdg.util;

public class CdGMath {
    public static int clamp(int val, int min, int max){
        return Math.min(max,Math.max(min,val));
    }
}
