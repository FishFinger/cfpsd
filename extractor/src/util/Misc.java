package util;

import java.util.ArrayList;
import java.util.LinkedList;
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
}
