package com.npc.swf.filter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.npc.swf.SwfContext;

public class DFASensitiveWordsFilterTest {

  @Test
  public void testFilter() throws Exception {
    try {
      //File articleFile = new File("/home/fudw/filterTest/article_300M");
      File articleFile = new File("/home/fudw/filterTest/article_all_sensitive_word_300M");
      List<String> list = FileUtils.readLines(new File("/home/fudw/filterTest/keywords.txt"));
      long articleSize = FileUtils.sizeOf(articleFile);
      System.out.println("过滤字符：" + list.size() + "个");
      System.out.println("文件大小:" + (articleSize / 1024.0 / 1024.0));
      long size = 0;
      long time = 0;
      for (int i = 0; i < 10; i++) {
        System.out.println("第"+(i+1)+"次测试 ===================================================");
        size += articleSize;
        long start = System.currentTimeMillis();
        String[] sw = list.toArray(new String[list.size()]);
        Map<Character, SensitiveWordsNode> swDic = SensitiveWordsBuider.build(sw);
        SwfContext sc = new SwfContext(swDic);

        System.out.println("初始化时间:" + (System.currentTimeMillis() - start) / 1000.0d);
        long start2 = System.currentTimeMillis();

        DFASensitiveWordsFilter filter = new DFASensitiveWordsFilter(sc);

        String article = FileUtils.readFileToString(articleFile);
        // 处理敏感字符
        long swc = filter.filter(article).getSensitiverWordNumber();
        System.out.println("敏感字个数:" + swc);
        long end = System.currentTimeMillis();
        long current = end - start2;
        time += current;
        System.out.println("过滤时间:" + (end - start2) / 1000.0d);
      }

      System.out.println();
      System.out.println("处理文件大小：" + size + "字符," + " 合：" + (size / 1024.0 / 1014.0) + "M");
      System.out.println("处理时间：" + time + "毫秒" + "  合：" + (time / 1000.0) + "秒");
      System.out.println("处理速度" + ((size / 1024.0 / 1014.0) / (time / 1000.0)) + " M/s");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    //fileProcess();
  }


  
}
