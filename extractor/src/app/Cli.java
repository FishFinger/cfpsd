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
    Scanner scan = new Scanner(System.in);
    String text = "";
    
    while( scan.hasNextLine())
      {
        text += scan.nextLine();
      }
    
    System.out.println(searchDate("iest 12/01/2004 siuate"));
    
 
 
  }
  
  
  private static LinkedList<DateWithPosition> searchDate(String text)
  {
    
    
    return new LinkedList<DateWithPosition>();
  }

}
