package javaT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PiSearch {
    public static void main(String[] args) {
        String filePath = "pi_digits.txt"; // 假设我们有一个包含π小数点后数据的文件
        String N = "6939937510"; // 目标数字序列
        int targetOccurrence = 99; // 查找第99次出现
        int position = -1; // 用于保存目标位置
        int count = 0; // 出现计数

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder piDecimals = new StringBuilder();
            String line;

            // 读取文件内容
            while ((line = br.readLine()) != null) {
                piDecimals.append(line.trim());
            }

            // 查找数字序列N的第99次出现位置
            for (int i = 0; i <= piDecimals.length() - N.length(); i++) {
                // 检查当前位置的子串是否匹配
                if (piDecimals.substring(i, i + N.length()).equals(N)) {
                    count++; // 找到一次匹配，计数器+1
                    if (count == targetOccurrence) {
                        position = i; // 第99次出现的位置
                        break; // 找到后停止
                    }
                }
            }

            // 输出结果
            if (position != -1) {
                System.out.println("数字序列 " + N + " 在 π 小数点后第 " + targetOccurrence + " 次出现的位置为：" + position);
            } else {
                System.out.println("数字序列 " + N + " 在 π 小数点后未找到第 " + targetOccurrence + " 次出现。");
            }

        } catch (IOException e) {
            System.out.println("文件读取出错: " + e.getMessage());
        }
    }
}
