package app.junit;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class RunJUnit
{
  public static void main(String[] args) {
    
    JUnitCore runner = new JUnitCore();
    runner.addListener(new TextListener(System.out));
    
    runner.run(DatePatternTest.class);

    runner.run(SearchDateTest.class);
    
  }
  
  
}
