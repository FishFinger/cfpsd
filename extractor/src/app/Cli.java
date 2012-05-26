package app;

import java.util.LinkedList;
import java.util.List;
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
   
    
    System.out.println(searchDate("i 12-12-201423434 12 01 2004 est 12/01/04a si 12-01-2004 ua12te"));
    
 
 
  }
  
  private static String read()
  {
    Scanner scan = new Scanner(System.in);
    String text = "";
    
    while( scan.hasNextLine())
      {
        text += scan.nextLine();
      }
    
    return text;
  }
  
  
  public static LinkedList<DateWithPosition> searchDate(String text)
  {
    LinkedList<DateWithPosition> list = new LinkedList<DateWithPosition>();
    
    final String P_DAY = "(0?[1-9]|[12][0-9]|3[01])";
    final String P_MONTH = "(0?[1-9]|1[012])";
    final String P_YEAR = "((2[0-9])?[0-9][0-9])";
    final String P_SEP = "[-/]";
    
    Pattern p = Pattern.compile(
        "(" + P_DAY + P_SEP + 
        P_MONTH + P_SEP +
        P_YEAR + ")\\D"
        );
    Matcher matcher = p.matcher(text);    
        
    while(matcher.find())
      list.add(new DateWithPosition(matcher.start(), matcher.group(1)));
      
    
    return list;
  }

}
