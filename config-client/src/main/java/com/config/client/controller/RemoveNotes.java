package com.config.client.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class RemoveNotes {
    public static void main(String[] args) {
        String path = "需要遍历的文件夹路径";
        File file = new File(path);
        func(file);
    }

    private static void func(File file) {
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) func(f);
            if (f.isFile()) {
                String text = ReadFileToString(f.getPath());
                text = removeCommentsWithQuoteAndDoubleEscape(text);
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter(f.getPath()));
                    pw.print(text);
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public static String removeCommentsWithQuoteAndDoubleEscape(String code) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        boolean quoteFlag = false;
        for (int i = 0; i < code.length(); i++) {
            if (!quoteFlag) {
                if (code.charAt(i) == '\"') {
                    sb.append(code.charAt(i));
                    quoteFlag = true;
                    continue;
                }
                //处理双斜杠注释
                else if (i + 1 < code.length() && code.charAt(i) == '/' && code.charAt(i + 1) == '/') {
                    while (code.charAt(i) != '\n') {
                        i++;
                    }
                    continue;
                }
                //不在双引号范围内
                else {
                    //处理/**/注释段
                    if (cnt == 0) {
                        if (i + 1 < code.length() && code.charAt(i) == '/' && code.charAt(i + 1) == '*') {
                            cnt++;
                            i++;
                            continue;
                        }
                    } else {
                        //发现"*/"结尾
                        if (i + 1 < code.length() && code.charAt(i) == '*' && code.charAt(i + 1) == '/') {
                            cnt--;
                            i++;
                            continue;
                        }
                        //发现""嵌套
                        if (i + 1 < code.length() && code.charAt(i) == '/' && code.charAt(i + 1) == '*') {
                            cnt++;
                            i++;
                            continue;
                        }
                    }
                    //如果没有发现/**/注释段或者已经处理完了嵌套的/**/注释段
                    if (cnt == 0) {
                        sb.append(code.charAt(i));
                        continue;
                    }
                }
            }
            //处理双引号注释段
            else {
                //如果发现双引号结束(非转义形式的双引号)
                if (code.charAt(i) == '\"' && code.charAt(i - 1) != '\\') {
                    sb.append(code.charAt(i));
                    quoteFlag = false;
                }
                //双引号开始了但是还没有结束
                else {
                    sb.append(code.charAt(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 从一个文件读入到String
     *
     * @param FilePath
     * @return
     */
    public static String ReadFileToString(String FilePath) {
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(FilePath);
            br = new BufferedReader(new InputStreamReader(fis, ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //构建成String
        StringBuffer sb = new StringBuffer();
        String temp = null;
        try {
            while ((temp = br.readLine()) != null) {
                sb.append(temp + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
