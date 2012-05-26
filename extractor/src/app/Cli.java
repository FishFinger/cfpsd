package app;

import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cli
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    String text = "";
    
    while( scan.hasNextLine())
      {
        text += scan.nextLine();
      }
    
    System.out.println(searchDate("iest 12/01/2004 siuate"));
    
 
 
  }
  
  
  private static String[] searchDate(String text)
  {
    String[] res;
    
    Pattern p = Pattern.compile("[0-9]{1,2}/[0-9]{1,2}/[0-9]{2}");
    Matcher m = p.matcher(text);    
    MatchResult result = m.toMatchResult();
    
    res = new String[result.groupCount()];
    for (int i=1; i<=result.groupCount(); i++)
        res[i] = result.group(i);
    
    return res;
  }

}
