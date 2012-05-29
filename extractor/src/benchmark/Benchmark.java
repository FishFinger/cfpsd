package benchmark;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Benchmark
{
  
  public static void main(String args[])
  {
    
  }
  
  private static LinkedList<String> searchDeadlineDateByMarkup(String text)
  {
    LinkedList<String> list = new LinkedList<String>();

    Pattern p = Pattern.compile("<deadline>(.*)</deadline>",
        Pattern.CASE_INSENSITIVE);
    Matcher matcher = p.matcher(text);

    while (matcher.find())
      list.add(matcher.group(2));

    return list;
  }
}
