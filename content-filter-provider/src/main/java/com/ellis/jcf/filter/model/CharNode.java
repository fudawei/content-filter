package com.ellis.jcf.filter.model;

import java.util.Map;

/// <summary>
/// 敏感词树
/// </summary>
public class CharNode {
  
  private char value;
  private Map<Character, CharNode> nodes;
  private boolean end = false;
  private boolean head = false;
 
  public Map<Character, CharNode> getNodes() {
    return nodes;
  }
  public void setNodes(Map<Character, CharNode> nodes) {
    this.nodes = nodes;
  }
  public boolean isEnd() {
    return end;
  }
  public void setEnd(boolean end) {
    this.end = end;
  }
 
  public char getValue() {
    return value;
  }
  public void setValue(char value) {
    this.value = value;
  }
  public boolean isHead() {
    return head;
  }
  public void setHead(boolean head) {
    this.head = head;
  }
  @Override
  public String toString() {
    return "[value=" + value + ", nodes=" + nodes +"]";
  }
}
