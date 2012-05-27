package app.junit;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Misc;

import app.DatePattern;

public class DatePatternTest
{

  @Before
  public void setUp() throws Exception
  {
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void testGetSuperPattern()
  {
    int val = Misc.countBracket(DatePattern.getSuperPattern().toString());
    assertTrue(val == 0);
  }

  @Test
  public void testGetNLPatternLocale()
  {
    int val = Misc.countBracket(DatePattern.getNLPattern(new Locale("fr")));
    assertTrue(val == 0);
  }

}
