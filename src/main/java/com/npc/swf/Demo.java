package com.npc.swf;

import com.npc.swf.filter.DFASensitiveWordsFilter;
import com.npc.swf.filter.Resoult;
import com.npc.swf.filter.SensitiveWordsBuider;
import com.npc.swf.filter.SensitiveWordsNode;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by fudw on 16-10-23.
 */
public class Demo {

  static final long M = 1000 * 1000L;
  static final long SIZE_OF_FILE_TO_FILTER = 1000; //  unit: M

  static final String  SENSITIVIE_WORDS = "keywords.txt";
  static final String  GENERAL_ARTICLE = "article.txt";
  static final String  ALL_SENSITIVE_WORD_ARTICLE = "article_all_sensitive_word.txt";
  static final String GENERAL_ARTICLE_BIG = "article_"+SIZE_OF_FILE_TO_FILTER+"M.txt";
  static final String ALL_SENSITIVE_WORD_ARTICLE_BIG = "article_all_sensitive_word_"+SIZE_OF_FILE_TO_FILTER+"M.txt";


  static ClassLoader classLoader = Demo.class.getClassLoader();




  public static void main(String[] args) throws Exception {

    // run demo
    //Demo();
    // run  performance testing
    test();


  }



  private static void Demo() throws Exception {

    // article to filterI
    String article = IOUtils.toString(classLoader.getResourceAsStream(GENERAL_ARTICLE));
    // sensitive word used to filter article
    List<String> list = IOUtils.readLines(classLoader.getResourceAsStream(SENSITIVIE_WORDS));
    String[] sw = list.toArray(new String[list.size()]);

    // init env for filter sensitive words
    Map<Character, SensitiveWordsNode> swDic = SensitiveWordsBuider.build(sw);
    SwfContext sc = new SwfContext(swDic);
    DFASensitiveWordsFilter filter = new DFASensitiveWordsFilter(sc);

    // filter article
    Resoult rs = filter.filter(article);
    // print filter resoult
    System.out.println(rs);
  }


  private static void test()  throws Exception {
    // propare data
    String path = classLoader.getResource("").getPath() + "/";
    fileProcess(path + GENERAL_ARTICLE, path + GENERAL_ARTICLE_BIG);
    fileProcess(path + ALL_SENSITIVE_WORD_ARTICLE, path + ALL_SENSITIVE_WORD_ARTICLE_BIG);

    // run test
    // general article test
    System.out.println("General article test ......");
    testAllsession(GENERAL_ARTICLE_BIG);
    System.out.println();

    // all sensitive words article test
    System.out.println("All sensitive words article test ......");
    testAllsession(ALL_SENSITIVE_WORD_ARTICLE_BIG);


    //delete test data
    File fileToDel1 = new File(path + GENERAL_ARTICLE_BIG);
    File fileToDel2 = new File(path + ALL_SENSITIVE_WORD_ARTICLE_BIG);

    if (fileToDel1.exists()) fileToDel1.delete();
    if (fileToDel2.exists()) fileToDel2.delete();
  }

  private static void testAllsession(String articleFileName) throws Exception {
    try {
      String path = classLoader.getResource("").getPath() + "/";
      File articleFile = new File(path + articleFileName);
      List<String> list = IOUtils.readLines(classLoader.getResourceAsStream(SENSITIVIE_WORDS));
      String article = FileUtils.readFileToString(articleFile);
      long articleSize = FileUtils.sizeOf(articleFile);
      long start = System.currentTimeMillis();


      String[] sw = list.toArray(new String[list.size()]);
      Map<Character, SensitiveWordsNode> swDic = SensitiveWordsBuider.build(sw);
      SwfContext sc = new SwfContext(swDic);
      DFASensitiveWordsFilter filter = new DFASensitiveWordsFilter(sc);
      System.out.println("Init  time:                 " + (System.currentTimeMillis() - start) / 1000.0d + "s");


      long start2 = System.currentTimeMillis();

      // 处理敏感字符
      long swc = filter.filter(article).getSensitiverWordNumber();

      long end = System.currentTimeMillis();
      long time = end - start2;

      System.out.println("Size of article to filter:" + articleSize + "B, " + " as：" + (articleSize / 1000.0 / 1000.0) + "M");
      System.out.println("Process :                 " + time + "ms" + "  as：" + (time / 1000.0) + "秒");
      System.out.println("Process speed :           " + ((articleSize / 1024.0 / 1014.0) / (time / 1000.0)) + " M/s");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  private static void fileProcess(String oldFile, String newFile) {
    try {
      String article =
              FileUtils.readFileToString(new File(oldFile));
      while (true) {
        File file = new File(newFile);
        if(!file.exists()) file.createNewFile();
        if(FileUtils.sizeOf(file) >= SIZE_OF_FILE_TO_FILTER *M) break;
        FileUtils.writeStringToFile(file, article,
                true);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
