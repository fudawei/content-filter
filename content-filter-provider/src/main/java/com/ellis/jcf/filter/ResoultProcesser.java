package com.ellis.jcf.filter;

import com.ellis.jcf.filter.model.Resoult;

/**
 * Created by fudaw on 2016/12/14.
 */
public interface ResoultProcesser {

  void  addSensitiveWord(String sensitiveWord);

  Resoult filterResoult();
}
