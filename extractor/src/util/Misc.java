package util;


public class Misc
{

  
  public static String[] concatArrays(String[] array1, String[] array2)
  {
    String[] new_array = new String[array1.length + array2.length];
    
    int i=0, j=0;
    for(; i<array1.length; ++i)
      new_array[i] = array1[i];
    
    for(; j<array2.length; ++j)
      new_array[i+j] = array2[j];
    
    return new_array;
  }
  
  public static String[] removeEmpty(String[] array)
  {
    String[] new_array;
    
    int size = 0;
    for(String s : array)
      if(s.length() > 0)
        ++size;
    
    new_array = new String[size];
    for(int i=0, j=0; i<array.length; ++i)
      if(array[i].length() > 0)
        new_array[j++] = array[i];
        
    
    
    return new_array;
  }
  
  /*
   * Compte les parenthèse +1 si ouvrante -1 si fermante
   */
  public static int countBracket(String s)
  {
    int val = 0;
    char c;
    for(int i=0; i<s.length(); ++i)
      {
        c = s.charAt(i);
        if(c == '(')
          ++val;
        else if(c == ')')
          --val;
      }
    
    return val;
  }
  
  public static void replace(String[] array, String string, String replacement)
  {
    for(int i=0; i<array.length; ++i)
      array[i] = array[i].replace(string, replacement);
  }
}
