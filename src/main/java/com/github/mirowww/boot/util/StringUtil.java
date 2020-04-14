package com.github.mirowww.boot.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringUtil {

    public static final String prefix = "com.xy.sts.street";

    public static List<String> trace(int skip, Predicate<StackTraceElement> filte, Function<StackTraceElement, String> fun) {
        StackTraceElement[] trace = new RuntimeException().getStackTrace();
        return Arrays.asList(trace).stream().skip(skip).filter(filte).map(fun).collect(Collectors.toList());
    }

    public static List<String> trace() {
        return trace(2, e -> {
            return e.getClassName().startsWith(prefix)
                    && !e.getClassName().contains("SpringCGLIB");
        }, e -> {
            return e.getClassName() + "." + e.getMethodName() + ":" + e.getLineNumber();
        });
    }
}
