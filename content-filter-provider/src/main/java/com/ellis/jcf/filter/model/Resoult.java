package com.ellis.jcf.filter.model;

import java.util.Map;

public class Resoult {
  
  private long sensitiverWordNumber;
  private Map<String, Integer> sensitiverWordCount;

  public Resoult(long sensitiverWordNumber, Map<String, Integer> sensitiverWordCount) {
    this.sensitiverWordNumber = sensitiverWordNumber;
    this.sensitiverWordCount = sensitiverWordCount;
  }
  
  public long getSensitiverWordNumber() {
    return sensitiverWordNumber;
  }
  public Map<String, Integer> getSensitiverWordCount() {
    return sensitiverWordCount;
  }

  @Override
  public String toString() {
    return "Resoult [sensitiverWordNumber=" + sensitiverWordNumber + ", sensitiverWordCount="
        + sensitiverWordCount + "]";
  }
  
}
