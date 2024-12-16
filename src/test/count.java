package test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class count {
    public static void main(String[] args) {
       /* // 创建 Scanner 对象，准备从控制台读取输入
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();

        // 提示用户输入
        System.out.println("请输入学生名单，每个学生用顿号（、）分隔，每行输入一个班级。输入完成后按 Enter 输入 Ctrl+D（或 Ctrl+Z）结束：");

        // 读取输入数据直到用户按 Ctrl+D（或 Ctrl+Z）结束
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break; // 如果输入为空行，退出输入
            }
            input.append(line).append("\n");
        }

        // 使用一个 Set 来存储学生姓名，自动去重
        Set<String> studentSet = new HashSet<>();
        // 用于检查是否有重复的名字
        boolean hasDuplicates = false;

        // 通过换行符将输入文本按行分割
        String[] lines = input.toString().split("\\n");

        for (String line : lines) {
            // 每行的姓名可能用顿号“、”分隔
            String[] students = line.split("、");

            for (String student : students) {
                // 如果 Set 已经包含这个学生的名字，说明有重复
                if (!studentSet.add(student.trim())) { // 使用 trim() 去除可能的空格
                    hasDuplicates = true;
                }
            }
        }

        // 统计总人数
        int totalStudentCount = studentSet.size();

        // 输出结果
        System.out.println("总人数：" + totalStudentCount);
        System.out.println("是否有重复的名字： " + (hasDuplicates ? "是" : "否"));*/

       String[] word = "dog cat cat fish".split(" ");

        for (String s : word) {
            System.out.println(s);
        }

        Map<String, Character> map = new HashMap<>();

    }
}
