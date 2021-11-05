package vn.hoapm.springboot.application.applyknowledge;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class xmxm {
    public static String RunLength(String str) {
        Map<Character, Integer> map = new HashMap<>();
        int size = str.length();
        int count = 1;
        String result = "";
        for (int i = 0; i < size - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                map.put(str.charAt(i), count);
                result = result + count + str.charAt(i);
                count = 1;
                map = new HashMap<>();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(RunLength("aabbcde"));
    }
}
