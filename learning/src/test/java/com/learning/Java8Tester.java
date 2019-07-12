package com.learning;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Java8Tester {

    @Test
    public void lambdaTest() {
        // 1. 实现Runnable线程案例
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before java 8");
            }
        }).start();

        // lambda 表达式 - java 8
        // Runnable r = () -> System.out.println("in java 8!");
        new Thread(() -> System.out.println("in java 8")).start();
    }

    @Test
    public void lambdaTest02() {
        //Prior Java 8 :
        List features = Arrays.asList("Lambdas", "Default Method",
                "Stream API", "Date and Time API");
        /*for (Object feature : features) {
            System.out.println(feature);
        }*/

        //In Java 8:
        features.forEach(n -> System.out.println(n));
        System.out.println("---------------------");

        // Even better use Method reference feature of Java 8
        // method reference is denoted by :: (double colon) operator
        // looks similar to score resolution operator of C++
        features.forEach(System.out::println);  // 方法引用是使用两个冒号::这个操作符号

    }


    /**
     * lambda表达式配合Java8新特性Stream API可以将业务功能通过函数式编程简洁的实现。(为后面的例子做铺垫)
     */
    @Test
    public void lambdaStreamAPI() {
        List<String> proNames = Arrays.asList(new String[]{"Ni", "Hao", "Lambda"});

        List<String> lowercaseNames = new ArrayList<String>();
        for (String str : proNames) {
            lowercaseNames.add(str.toLowerCase());
        }
        System.out.println(lowercaseNames);

        // lambda
        // 这段代码就是对一个字符串的列表，把其中包含的每个字符串都转换成全小写的字符串。注意代码第四行的map方法调用，这里map方法就是接受了一个lambda表达式
        List<String> lowercaseNames1 = proNames.stream().map(name -> {return name.toLowerCase();}).collect(Collectors.toList());
        System.out.println(lowercaseNames1);
    }

    /**
     * java 8 Stream
     */
    @Test
    public void java8Test() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);
        Random random = new Random();

        System.out.println("使用 Java 8: ");
        System.out.println("列表: " + strings);

        long count = strings.stream().filter(string->string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        List<String> filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        String mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        List<Integer> squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " + integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);


        count = strings.stream().filter(str -> str.isEmpty()).count();
        System.out.println("count: " + count);

        long lengthCount = strings.stream().filter(str -> str.length() == 3).count();
        System.out.println("lengthCount: " + lengthCount);

    }

    @Test
    public void java8DateTime() {
        LocalTime now = LocalTime.now();
        LocalTime time = LocalTime.of(15, 30, 30);// hour, minute, second
        boolean compare = now.isBefore(time);
        System.out.println("日期比较(时分秒)： " + compare);

        LocalDateTime dateTimeNow = LocalDateTime.now();
        // 控制台输出： 2019-07-11T10:23:23.093
        System.out.println("localDateTime 时间: " + dateTimeNow);
        System.out.println("LocalDateTime 转 LocalDate ： " + dateTimeNow.toLocalDate());
        LocalDateTime dateTime = LocalDateTime.of(2019, 02, 28, 19, 00, 00);
        int compareTo = dateTimeNow.compareTo(dateTime);
        System.out.println("LocalDateTime (compareTo) 比较：" + compareTo);

        LocalDateTime dateTimeAfter30 = dateTimeNow.plusDays(30);
        System.out.println("30 天后时间： " + dateTimeAfter30);

        LocalDateTime minus = dateTimeNow.minus(1, ChronoUnit.HOURS);// 一小时前的时间
        System.out.println("1小时前的时间: " + minus);

        Clock clock = Clock.systemDefaultZone();
        Clock clock1 = Clock.systemUTC();
        // 控制台输出: systemDefaultZone: SystemClock[Asia/Shanghai], systemUTC: SystemClock[Z]
        System.out.println("systemDefaultZone: " + clock + ", systemUTC: " + clock1);


        Period between = Period.between(dateTimeNow.toLocalDate(), dateTimeAfter30.toLocalDate());
        System.out.println("两个日期(LocalDate)之间比较, 相差: " + between.getDays() + " 天");

        System.out.println("获取当前的时间戳： " + (int) dateTimeNow.toInstant(ZoneOffset.of("+8")).getEpochSecond());


        // 日期格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parse = LocalDate.parse(dateTimeNow.toLocalDate().toString(), dateTimeFormatter);
        System.out.println("LocalDate 解析: " + parse);

        String format = dateTimeNow.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("格式化 LocalDateTime 成 yyyyMMdd : " + format);

        // 时间比较
        String defineTime = "2019-03-14 11:57:39";
        LocalDateTime localDateTime = LocalDateTime.parse(defineTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDateTimeAfterMonth = localDateTime.plus(1, ChronoUnit.MONTHS);

        if (dateTimeNow.isBefore(localDateTimeAfterMonth)) {
            System.out.println("当前时间( " + dateTimeNow + " ) 小于指定时间( " + localDateTimeAfterMonth + " )");
        } else {
            System.out.println("当前时间( " + dateTimeNow + " ) 不小于指定时间( " + localDateTimeAfterMonth + " )");
        }

        // string - localDateTime - (yyyyMMdd) - (yyyy-MM-dd) - (yyyy-MM-dd HH:mm:ss)

    }

    @Test
    public void java8Base64() {

        String str = "abcdefghigklmnopqrsxyz";
        try {
            // 使用基本编码
            String base64encodedString = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);

            // 基本编码 解码
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));

            // 使用 URL 编码
            base64encodedString = Base64.getUrlEncoder().encodeToString(str.getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);

            byte[] urlDecodeBytes = Base64.getUrlDecoder().decode(base64encodedString);
            System.out.println("URL 编码 解码出来的字符串: " + new String(urlDecodeBytes, "utf-8"));

            // MIME 编码
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(str.getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);

            byte[] mimeDecode = Base64.getMimeDecoder().decode(mimeEncodedString);
            System.out.println("MIME 编码 解码出来的字符串： " + new String(mimeDecode, "utf-8"));


            // 使用 MIME 编码
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }
            System.out.println("MIME 编码原始字符串: " + stringBuilder.toString());
            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);

        }catch(UnsupportedEncodingException e){
            System.out.println("Error :" + e.getMessage());
        }
    }

}
