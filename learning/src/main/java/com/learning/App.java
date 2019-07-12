package com.learning;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );

        /*User user = (User) SpringUtil.getBean("user");
        System.out.print(user);*/

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        do {
            s = reader.readLine();
            System.out.println("读取到的字符串： " + s);
        } while (s != "q");*/


        String str = "111";
        String a = "111";
        String str2 = a + "";       // 先到内存找变量对应的值, 走StringBuilder进行拼接字符串, 内存地址不同
        String str3 = "111" + "";   // 不用根据变量去内存里找对应的值, 在常量池中查找
        System.out.println(str == str2);// false
        System.out.println(str == str3);// true

    }
}
