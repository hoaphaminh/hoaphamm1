package vn.hoapm.springboot.application.applyknowledge;

import java.util.List;
import java.util.stream.Collectors;

public class JavaStream {
    private JavaStream(){}
    public static int devine(int dividend, int divitor) {
        if (divitor == 0) {
            throw new IllegalArgumentException("Can't devide by zero");
        } else return (dividend / divitor);
    }

    public static List<String> applyStream(List<String> input) {
        return input.stream().parallel().filter(e -> e.length()<3).map(String::toUpperCase).collect(Collectors.toList());
    }
}
