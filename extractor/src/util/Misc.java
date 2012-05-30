package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Misc
{

  public static String[] concatArrays(String[] array1, String[] array2)
  {
    String[] new_array = new String[array1.length + array2.length];

    int i = 0, j = 0;
    for (; i < array1.length; ++i)
      new_array[i] = array1[i];

    for (; j < array2.length; ++j)
      new_array[i + j] = array2[j];

    return new_array;
  }

  public static String[] removeEmpty(String[] array)
  {
    String[] new_array;

    int size = 0;
    for (String s : array)
      if (s.length() > 0)
        ++size;

    new_array = new String[size];
    for (int i = 0, j = 0; i < array.length; ++i)
      if (array[i].length() > 0)
        new_array[j++] = array[i];

    return new_array;
  }

  /* Compte les parenthèse +1 si ouvrante -1 si fermante */
  public static int countBracket(String s)
  {
    int val = 0;
    char c;
    for (int i = 0; i < s.length(); ++i)
      {
        c = s.charAt(i);
        if (c == '(')
          ++val;
        else if (c == ')')
          --val;
      }

    return val;
  }

  public static void replace(String[] array, String string, String replacement)
  {
    for (int i = 0; i < array.length; ++i)
      array[i] = array[i].replace(string, replacement);
  }

  public static LinkedList<String> getNewByReplace(String[] array,
      char old_char, char new_char)
  {
    LinkedList<String> list = new LinkedList<String>();

    String new_s;
    for (String s : array)
      {
        new_s = s.replace(old_char, new_char);
        if (!s.equals(new_s))
          list.add(new_s);
      }

    return list;
  }

  public static String removeHeader(String text)
  {
    try
      {
        Pattern p = Pattern.compile("\n\n(.*)", Pattern.MULTILINE);
        Matcher m = p.matcher(text);

        m.find();

        return text.substring(m.start(1));
      }
    catch (Exception e)
      {
        return "";
      }
  }

  public static String read(File file) throws FileNotFoundException, UnsupportedEncodingException
  {
    return read(new FileInputStream(file));
  }

  public static String read(InputStream stream) throws UnsupportedEncodingException
  {
    Scanner scan;

    scan = new Scanner(new InputStreamReader(stream, "UTF8"));
    String text = "";

    while (scan.hasNextLine())
      {
        text += scan.nextLine() + "\n";
      }

    return text;

  }
  
  private static short percent = -1, old_percent = 0;
  private static char[] anim = {'-', '\\','|','/'};
  private static int current_char = -1;
  public static void printPercent(int actual, int total)
  {
    percent = (short) (actual*100 / total);
    
    if(percent != old_percent)
      System.out.println(percent + "%"+anim[++current_char % anim.length]);
    
    /*String s = "";
    if(percent != old_percent)
      {
        if(old_percent < 0)
          s = percent + "%";
        else if(old_percent < 10)
          s ="\b\b\b"+ percent + "%";
        else 
          s ="\b\b\b\b"+ percent + "%";
        
        System.out.print(s+anim[++current_char % anim.length]);
      }
    else  
      System.out.print("\b"+anim[++current_char % anim.length]);*/

    old_percent = percent;

    
  }
}
