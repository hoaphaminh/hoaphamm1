package vn.hoapm.springboot.application.applyknowledge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public interface Action {
        public String convert(String input);
    }

    public static void convert(String input, Action action) {
        System.out.println(action.convert(input));
    }

    public static void main(String[] args) {
        String input = "hoaphamssMinh";

//       Test about lambda
//        convert(input, new Action() {
//            @Override
//            public String convert(String input) {
//                return input.toLowerCase();
//            }
//        });
//        convert(input, new Action() {
//            @Override
//            public String convert(String input) {
//                return input.toUpperCase();
//            }
//        });
//        convert(input, chuoi -> chuoi.toLowerCase());
//        convert(input, chuoi -> chuoi.toUpperCase());
//        convert(input, chuoi -> {
//            return chuoi.toLowerCase();
//        });
//        convert(input, chuoi -> {
//            return chuoi.toUpperCase();
//        });
//        convert(input, String::toLowerCase);
//        convert(input, String::toUpperCase);
        Random  rd = new Random();
        int size = 10;

        List<Integer> mangInteger = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mangInteger.add(rd.nextInt());
        }

        // execute normal
        long startNormal = System.nanoTime();
            Long rs = mangInteger.stream().filter(s->{
            Double can = (Double) Math.sqrt(s);
            return (can- Math.floor(can)==0);
        }).count();
        long endNormal = System.nanoTime();
        System.out.println("Normal:"+ ( endNormal-startNormal));

        //execute with parallel
        long startParallel = System.nanoTime();
        Long rsParallel = mangInteger.stream().parallel().filter(s->{
            Double can = (Double) Math.sqrt(s);
            return (can- Math.floor(can)==0);
        }).count();
        long endParallel = System.nanoTime();
        System.out.println("parallel:" + (endParallel-startParallel));

        System.out.println("Distance:" + (( endNormal-startNormal)/(endParallel-startParallel)));


        // Test about stream
//       List <String> mang = Arrays.asList("Day", "la","vi","du","ve","hoa","stream","pham", "minh" ,"java");
//        List<String> result = mang.stream().filter(s -> {
//            return s.length()>2;
//        }).map(String::toUpperCase).limit(3).collect(Collectors.toList());
//        result.stream().forEach(e-> System.out.println(e));
    }
}
