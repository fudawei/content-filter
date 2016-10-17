package com.npc.swf.utils;

public class Config {
  private String sensitiveWordsPath = "/";
  private long articleLengthLimit = 10000L;
  private OutPutType outPutType = OutPutType.LOCAL_FILE;
}

enum OutPutType {
  DB,ES,MONGO,HDFS,EXCEL,LOCAL_FILE
}
 