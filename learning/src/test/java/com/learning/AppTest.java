package com.learning;

import com.learning.bean.User;
import com.learning.config.BeanConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void applicationTest() {
        /*User user = (User) SpringUtil.getBean("user");
        System.out.print(user);*/

        List<Object> objects = new ArrayList<>();

        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        User user = (User) applicationContext.getBean("messageService");
        System.out.println(user.getName());*/

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanConfig.class);
        applicationContext.refresh();

        //User user = applicationContext.getBean(User.class);
        User user = (User) applicationContext.getBean("user");
        System.out.println("name: " + user.getName() + ", age: " + user.getAge());

    }

    @Test
    public void streamTest() throws IOException {
        // System.in 捕获控制台输入的字符串
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // file -> fileReader -> BufferedReader -> lineContent = bufferedReader.realLine()
        // 读取
        File file = new File("D:\\test.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // 写入文件
        // file -> fileWrite -> BufferedWriter -> bufferedWriter.write(); newLine(); flush();
        File fileWrite = new File("D:\\write.txt");
        FileWriter fileWriter = new FileWriter(fileWrite);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            String lineContent = "";

            StringBuilder builder = new StringBuilder();
            while (bufferedReader.ready() && (lineContent = bufferedReader.readLine()) != null) {
                // 文件内容 转成 字符串
                builder.append(lineContent + "\n");

                // 文件内容 转成 新的文件
                bufferedWriter.write(lineContent);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            System.out.println(builder.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }

    @Test
    public void FileStreamTest() {

    }

    @Test
    public void fileTest() {

    }

    // 递归删除文件夹、文件
    public void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }

    @Test
    public void mapTest() {
        List<User> list = new ArrayList<User>();
        list.add(new User("张三", "28"));
        list.add(new User("李四", "19"));
        list.add(new User("王五", "20"));


        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }

        Collections.sort(list, (s1, s2) -> s1.getAge().compareTo(s2.getAge()));
        Collections.sort(list, (s1, s2) -> {return s1.getAge().compareTo(s2.getAge());});
        System.out.println("排序后的list-------");
        for (User user : list) {
            System.out.println("name: " + user.getName() + ", age: " + user.getAge());
        }
    }

    @Test
    public void stringTest() {
        String str = "111";
        String a = "111";
        String str2 = a + "";
        String str3 = "111" + "";

        System.out.println(str == str2);
        System.out.println(str == str3);
    }


}
