package app.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Core;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
public class SearchDateTest
{

  @Test
  public void testNotDate()
  {
    assertEquals(0, Core.searchDate("").size());
    assertEquals(0, Core.searchDate("Hello").size());
    assertEquals(0, Core.searchDate("Hi guy !").size());
    assertEquals(0, Core.searchDate("  I have 2 cats :)").size());
    assertEquals(0, Core.searchDate("My name is James").size());
    assertEquals(0, Core.searchDate("1 2 3 soleil").size());
    
    assertEquals(0, Core.searchDate("12112112").size());
    assertEquals(0, Core.searchDate("12a12a12").size());
    assertEquals(0, Core.searchDate("31 Poireaux 2014").size());
    //assertEquals(0, Core.searchDate("45 avril 2011)").size());
    assertEquals(0, Core.searchDate("32/12/12").size());
    assertEquals(0, Core.searchDate("13/13/12").size());
    assertEquals(0, Core.searchDate("12/32/12").size());
    
    assertEquals(0, Core.searchDate("01/12/12222").size());
  }
  
  @Test
  public void testBeginEnglish()
  {

    // Should be detected as dates
    assertEquals(1, Core.searchDate("March 25, 2005").size());
    assertEquals(1, Core.searchDate("April 25, 2005").size());
    assertEquals(1, Core.searchDate("May 10, 2005").size());
    assertEquals(1, Core.searchDate("June 6, 2005").size());
    assertEquals(1, Core.searchDate("15 Jun 2005").size());
    assertEquals(1, Core.searchDate("15 Aug 2005").size());
    assertEquals(1, Core.searchDate("15 Sep 2005").size());
    assertEquals(1, Core.searchDate("04 Jan 2006").size());
    assertEquals(1, Core.searchDate("4 Jan 2006").size());
    assertEquals(1, Core.searchDate("14 Feb 2005").size());
    assertEquals(1, Core.searchDate("24 September 2005").size());
    assertEquals(1, Core.searchDate("1st June 2005").size());
    assertEquals(1, Core.searchDate("1 June 2005").size());
    assertEquals(1, Core.searchDate("15 July 2005").size());
    assertEquals(1, Core.searchDate("10th August 2005").size());
    assertEquals(1, Core.searchDate("10 August 2005").size());
    assertEquals(1, Core.searchDate("March 9, 2005").size());
    assertEquals(1, Core.searchDate("May 24, 2005").size());
    assertEquals(1, Core.searchDate("June 3, 2005").size());
    assertEquals(1, Core.searchDate("22nd February 2005").size());
    assertEquals(1, Core.searchDate("15 April 2005").size());
    assertEquals(1, Core.searchDate("march 25, 2005").size());
    assertEquals(1, Core.searchDate("31 may 2005").size());
    assertEquals(1, Core.searchDate("31 May 2005").size());
    assertEquals(1, Core.searchDate("30 June 2005").size());
    assertEquals(1, Core.searchDate("30 June 05").size());
    assertEquals(1, Core.searchDate("30 june 05").size());
    assertEquals(1, Core.searchDate("2nd june 05").size());
    assertEquals(1, Core.searchDate("1st june 05").size());
    assertEquals(1, Core.searchDate("15 Jun. 2005").size());
    assertEquals(1, Core.searchDate("15 jun. 2005").size());
    assertEquals(1, Core.searchDate("15 Feb. 2005").size());
    assertEquals(1, Core.searchDate("15 feb. 2005").size());
    assertEquals(1, Core.searchDate("15 Apr. 2005").size());
    assertEquals(1, Core.searchDate("15 apr. 2005").size());
    assertEquals(1, Core.searchDate("15 Jul. 2005").size());
    assertEquals(1, Core.searchDate("15 jul. 2005").size());
    assertEquals(1, Core.searchDate("15 Aug. 2005").size());
    assertEquals(1, Core.searchDate("15 aug. 2005").size());
    assertEquals(1, Core.searchDate("15 Sep. 2005").size());
    assertEquals(1, Core.searchDate("15 sep. 2005").size());
    assertEquals(1, Core.searchDate("15 Oct. 2005").size());
    assertEquals(1, Core.searchDate("15 oct. 2005").size());
    assertEquals(1, Core.searchDate("15 Nov. 2005").size());
    assertEquals(1, Core.searchDate("15 nov. 2005").size());
    assertEquals(1, Core.searchDate("15 Dec. 2005").size());
    assertEquals(1, Core.searchDate("15 dec. 2005").size());
    assertEquals(1, Core.searchDate("15th dec. 2005").size());

    assertEquals(1, Core.searchDate("5 January 2005").size());
    assertEquals(1, Core.searchDate("5 February 2005").size());
    assertEquals(1, Core.searchDate("5 March 2005").size());
    assertEquals(1, Core.searchDate("5 April 2005").size());
    assertEquals(1, Core.searchDate("5 May 2005").size());
    assertEquals(1, Core.searchDate("5 Jun 2005").size());
    assertEquals(1, Core.searchDate("5 July 2005").size());
    assertEquals(1, Core.searchDate("5 August 2005").size());
    assertEquals(1, Core.searchDate("5 September 2005").size());
    assertEquals(1, Core.searchDate("5 October 2005").size());
    assertEquals(1, Core.searchDate("5 November 2005").size());
    assertEquals(1, Core.searchDate("5 December 2005").size());

    assertEquals(1, Core.searchDate("5 january 2005").size());
    assertEquals(1, Core.searchDate("5 february 2005").size());
    assertEquals(1, Core.searchDate("5 march 2005").size());
    assertEquals(1, Core.searchDate("5 april 2005").size());
    assertEquals(1, Core.searchDate("5 may 2005").size());
    assertEquals(1, Core.searchDate("5 jun 2005").size());
    assertEquals(1, Core.searchDate("5 july 2005").size());
    assertEquals(1, Core.searchDate("5 august 2005").size());
    assertEquals(1, Core.searchDate("5 september 2005").size());
    assertEquals(1, Core.searchDate("5 october 2005").size());
    assertEquals(1, Core.searchDate("5 november 2005").size());
    assertEquals(1, Core.searchDate("5 december 2005").size());
    
    assertEquals(1, Core.searchDate("5 Jan 2005").size());
    assertEquals(1, Core.searchDate("5 Feb 2005").size());
    assertEquals(1, Core.searchDate("5 Mar 2005").size());
    assertEquals(1, Core.searchDate("5 Apr 2005").size());
    assertEquals(1, Core.searchDate("5 May 2005").size());
    assertEquals(1, Core.searchDate("5 Jun 2005").size());
    assertEquals(1, Core.searchDate("5 Jul 2005").size());
    assertEquals(1, Core.searchDate("5 Aug 2005").size());
    assertEquals(1, Core.searchDate("5 Sep 2005").size());
    assertEquals(1, Core.searchDate("5 Oct 2005").size());
    assertEquals(1, Core.searchDate("5 Nov 2005").size());
    assertEquals(1, Core.searchDate("5 Dec 2005").size());

    assertEquals(1, Core.searchDate("5 jan 2005").size());
    assertEquals(1, Core.searchDate("5 feb 2005").size());
    assertEquals(1, Core.searchDate("5 mar 2005").size());
    assertEquals(1, Core.searchDate("5 apr 2005").size());
    assertEquals(1, Core.searchDate("5 may 2005").size());
    assertEquals(1, Core.searchDate("5 jun 2005").size());
    assertEquals(1, Core.searchDate("5 jul 2005").size());
    assertEquals(1, Core.searchDate("5 aug 2005").size());
    assertEquals(1, Core.searchDate("5 sep 2005").size());
    assertEquals(1, Core.searchDate("5 oct 2005").size());
    assertEquals(1, Core.searchDate("5 nov 2005").size());
    assertEquals(1, Core.searchDate("5 dec 2005").size());

    assertEquals(1, Core.searchDate("5 Jan. 2005").size());
    assertEquals(1, Core.searchDate("5 Feb. 2005").size());
    assertEquals(1, Core.searchDate("5 Mar. 2005").size());
    assertEquals(1, Core.searchDate("5 Apr. 2005").size());
    assertEquals(1, Core.searchDate("5 May. 2005").size());
    assertEquals(1, Core.searchDate("5 Jun. 2005").size());
    assertEquals(1, Core.searchDate("5 Jul. 2005").size());
    assertEquals(1, Core.searchDate("5 Aug. 2005").size());
    assertEquals(1, Core.searchDate("5 Sep. 2005").size());
    assertEquals(1, Core.searchDate("5 Oct. 2005").size());
    assertEquals(1, Core.searchDate("5 Nov. 2005").size());
    assertEquals(1, Core.searchDate("5 Dec. 2005").size());

    assertEquals(1, Core.searchDate("5 jan. 2005").size());
    assertEquals(1, Core.searchDate("5 feb. 2005").size());
    assertEquals(1, Core.searchDate("5 mar. 2005").size());
    assertEquals(1, Core.searchDate("5 apr. 2005").size());
    assertEquals(1, Core.searchDate("5 may. 2005").size());
    assertEquals(1, Core.searchDate("5 jun. 2005").size());
    assertEquals(1, Core.searchDate("5 jul. 2005").size());
    assertEquals(1, Core.searchDate("5 aug. 2005").size());
    assertEquals(1, Core.searchDate("5 sep. 2005").size());
    assertEquals(1, Core.searchDate("5 oct. 2005").size());
    assertEquals(1, Core.searchDate("5 nov. 2005").size());
    assertEquals(1, Core.searchDate("5 dec. 2005").size());
    
  }

  @Test
  public void testEnglish()
  {
    assertEquals(0, Core.searchDate("15 April 2005").get(0).getPosition());
    assertEquals(1, Core.searchDate(" 15 April 2005").get(0).getPosition());
    assertEquals(2, Core.searchDate("  15 April 2005").get(0).getPosition());
    assertEquals(3, Core.searchDate("   15 April 2005").get(0).getPosition());
    assertEquals(4, Core.searchDate("    15 April 2005").get(0).getPosition());
    assertEquals(5, Core.searchDate("     15 April 2005").get(0).getPosition());

    assertEquals(8, Core.searchDate("Hi guy: 15 April 2005").get(0)
        .getPosition());
    assertEquals(12, Core.searchDate("1 et 2 et 3 15 April 2005").get(0)
        .getPosition());
  }

  @Test
  public void testMultipleEnglish()
  {
    String date;

    date = "15 April 2005, 22 April 2005";
    assertEquals(2, Core.searchDate(date).size());
    assertEquals(0, Core.searchDate(date).get(0).getPosition());
    assertEquals(15, Core.searchDate(date).get(1).getPosition());

    date = "15 April 2005 et 22 April 2005";
    assertEquals(2, Core.searchDate(date).size());
    assertEquals(0, Core.searchDate(date).get(0).getPosition());
    assertEquals(17, Core.searchDate(date).get(1).getPosition());

    date = "come one ! the 15 April 2005, 22 April 2005 and also the 23 April 2005.";
    assertEquals(3, Core.searchDate(date).size());
    assertEquals(15, Core.searchDate(date).get(0).getPosition());
    assertEquals(30, Core.searchDate(date).get(1).getPosition());
    assertEquals(57, Core.searchDate(date).get(2).getPosition());
  }

  @Test
  public void testBeginFrench()
  {
    // Not a date
    assertEquals(0, Core.searchDate("").size());
    assertEquals(0, Core.searchDate("Bonjour").size());
    assertEquals(0, Core.searchDate("Salut mec !").size());
    assertEquals(0, Core.searchDate("  J'ai deux chats").size());
    assertEquals(0, Core.searchDate("Mon nom est James").size());
    assertEquals(0, Core.searchDate("1 2 3 soleil").size());

    // Should be detected as dates
    assertEquals(1, Core.searchDate("25 Mars 2005").size());
    assertEquals(1, Core.searchDate("25 mars 2005").size());
    assertEquals(1, Core.searchDate("1er Avril 2002").size());
    assertEquals(1, Core.searchDate("1 Avril 2002").size());
    assertEquals(1, Core.searchDate("1er avril 2002").size());
    assertEquals(1, Core.searchDate("1 avril 2002").size());
    assertEquals(1, Core.searchDate("1er avr. 2002").size());
    assertEquals(1, Core.searchDate("1er Avr. 2002").size());
    assertEquals(1, Core.searchDate("1er Avr 2002").size());

    assertEquals(1, Core.searchDate("3 Février 2005").size());
    assertEquals(1, Core.searchDate("3 février 2005").size());
    assertEquals(1, Core.searchDate("3 fév 2005").size());
    assertEquals(1, Core.searchDate("3 fév. 2005").size());
    assertEquals(1, Core.searchDate("3 Fév 2005").size());
    assertEquals(1, Core.searchDate("3 Fév. 2005").size());
    assertEquals(1, Core.searchDate("3 Fev. 2005").size());

    assertEquals(1, Core.searchDate("15 Septembre 2005").size());
    assertEquals(1, Core.searchDate("15 septembre 2005").size());
    assertEquals(1, Core.searchDate("15 Sept 2005").size());
    assertEquals(1, Core.searchDate("15 sept 2005").size());
    assertEquals(1, Core.searchDate("15 Sep 2005").size());
    assertEquals(1, Core.searchDate("15 sep 2005").size());
    assertEquals(1, Core.searchDate("15 Sept. 2005").size());
    assertEquals(1, Core.searchDate("15 sept. 2005").size());

    assertEquals(1, Core.searchDate("5 Janvier 2005").size());
    assertEquals(1, Core.searchDate("5 Février 2005").size());
    assertEquals(1, Core.searchDate("5 Mars 2005").size());
    assertEquals(1, Core.searchDate("5 Avril 2005").size());
    assertEquals(1, Core.searchDate("5 Mai 2005").size());
    assertEquals(1, Core.searchDate("5 Juin 2005").size());
    assertEquals(1, Core.searchDate("5 Juillet 2005").size());
    assertEquals(1, Core.searchDate("5 Août 2005").size());
    assertEquals(1, Core.searchDate("5 Septembre 2005").size());
    assertEquals(1, Core.searchDate("5 Octobre 2005").size());
    assertEquals(1, Core.searchDate("5 Novembre 2005").size());
    assertEquals(1, Core.searchDate("5 Décembre 2005").size());

    assertEquals(1, Core.searchDate("5 janvier 2005").size());
    assertEquals(1, Core.searchDate("5 février 2005").size());
    assertEquals(1, Core.searchDate("5 mars 2005").size());
    assertEquals(1, Core.searchDate("5 avril 2005").size());
    assertEquals(1, Core.searchDate("5 mai 2005").size());
    assertEquals(1, Core.searchDate("5 juin 2005").size());
    assertEquals(1, Core.searchDate("5 juillet 2005").size());
    assertEquals(1, Core.searchDate("5 août 2005").size());
    assertEquals(1, Core.searchDate("5 septembre 2005").size());
    assertEquals(1, Core.searchDate("5 octobre 2005").size());
    assertEquals(1, Core.searchDate("5 novembre 2005").size());
    assertEquals(1, Core.searchDate("5 décembre 2005").size());

    assertEquals(1, Core.searchDate("5 Jan 2005").size());
    assertEquals(1, Core.searchDate("5 Fév 2005").size());
    assertEquals(1, Core.searchDate("5 Mar 2005").size());
    assertEquals(1, Core.searchDate("5 Avr 2005").size());
    assertEquals(1, Core.searchDate("5 Juil 2005").size());
    assertEquals(1, Core.searchDate("5 Août 2005").size());
    assertEquals(1, Core.searchDate("5 Sept 2005").size());
    assertEquals(1, Core.searchDate("5 Oct 2005").size());
    assertEquals(1, Core.searchDate("5 Nov 2005").size());
    assertEquals(1, Core.searchDate("5 Déc 2005").size());

    assertEquals(1, Core.searchDate("5 jan 2005").size());
    assertEquals(1, Core.searchDate("5 fév 2005").size());
    assertEquals(1, Core.searchDate("5 mar 2005").size());
    assertEquals(1, Core.searchDate("5 avr 2005").size());
    assertEquals(1, Core.searchDate("5 juil 2005").size());
    assertEquals(1, Core.searchDate("5 août 2005").size());
    assertEquals(1, Core.searchDate("5 sept 2005").size());
    assertEquals(1, Core.searchDate("5 oct 2005").size());
    assertEquals(1, Core.searchDate("5 nov 2005").size());
    assertEquals(1, Core.searchDate("5 déc 2005").size());

    assertEquals(1, Core.searchDate("5 Jan. 2005").size());
    assertEquals(1, Core.searchDate("5 Fév. 2005").size());
    assertEquals(1, Core.searchDate("5 Mar. 2005").size());
    assertEquals(1, Core.searchDate("5 Avr. 2005").size());
    assertEquals(1, Core.searchDate("5 Juil. 2005").size());
    assertEquals(1, Core.searchDate("5 Août. 2005").size());
    assertEquals(1, Core.searchDate("5 Sept. 2005").size());
    assertEquals(1, Core.searchDate("5 Oct. 2005").size());
    assertEquals(1, Core.searchDate("5 Nov. 2005").size());
    assertEquals(1, Core.searchDate("5 Déc. 2005").size());

    assertEquals(1, Core.searchDate("5 jan. 2005").size());
    assertEquals(1, Core.searchDate("5 fév. 2005").size());
    assertEquals(1, Core.searchDate("5 mar. 2005").size());
    assertEquals(1, Core.searchDate("5 avr. 2005").size());
    assertEquals(1, Core.searchDate("5 juil. 2005").size());
    assertEquals(1, Core.searchDate("5 août. 2005").size());
    assertEquals(1, Core.searchDate("5 sept. 2005").size());
    assertEquals(1, Core.searchDate("5 oct. 2005").size());
    assertEquals(1, Core.searchDate("5 nov. 2005").size());
    assertEquals(1, Core.searchDate("5 déc. 2005").size());
  }
  
  @Test
  public void testFrenchWithoutAccent()
  {  
    assertEquals(1, Core.searchDate("3 Fevrier 2005").size());
    assertEquals(1, Core.searchDate("3 fev 2005").size());
    assertEquals(1, Core.searchDate("3 fev. 2005").size());
    assertEquals(1, Core.searchDate("3 Fev 2005").size());
    
    assertEquals(1, Core.searchDate("5 Fevrier 2005").size());
    assertEquals(1, Core.searchDate("5 Aout 2005").size());
    assertEquals(1, Core.searchDate("5 Decembre 2005").size());
    
    assertEquals(1, Core.searchDate("5 fevrier 2005").size());
    assertEquals(1, Core.searchDate("5 aout 2005").size());
    assertEquals(1, Core.searchDate("5 decembre 2005").size());
    
    assertEquals(1, Core.searchDate("5 Fev 2005").size());
    assertEquals(1, Core.searchDate("5 Aout 2005").size());
    assertEquals(1, Core.searchDate("5 Dec 2005").size());
    
    assertEquals(1, Core.searchDate("5 fev 2005").size());
    assertEquals(1, Core.searchDate("5 aout 2005").size());
    assertEquals(1, Core.searchDate("5 dec 2005").size());
    
    assertEquals(1, Core.searchDate("5 Fev. 2005").size());
    assertEquals(1, Core.searchDate("5 Aout. 2005").size());
    assertEquals(1, Core.searchDate("5 Dec. 2005").size());
    
    assertEquals(1, Core.searchDate("5 fev. 2005").size());
    assertEquals(1, Core.searchDate("5 aout. 2005").size()); 
    assertEquals(1, Core.searchDate("5 dec. 2005").size());
  }

  @Test
  public void testFrench()
  {
    assertEquals(0, Core.searchDate("15 Avril 2005").get(0).getPosition());
    assertEquals(1, Core.searchDate(" 15 Avril 2005").get(0).getPosition());
    assertEquals(2, Core.searchDate("  15 Avril 2005").get(0).getPosition());
    assertEquals(3, Core.searchDate("   15 Avril 2005").get(0).getPosition());
    assertEquals(4, Core.searchDate("    15 Avril 2005").get(0).getPosition());
    assertEquals(5, Core.searchDate("     15 Avril 2005").get(0).getPosition());

    assertEquals(12, Core.searchDate("Salut mec : 15 Avril 2005").get(0)
        .getPosition());
    assertEquals(12, Core.searchDate("1 et 2 et 3 15 Avril 2005").get(0)
        .getPosition());
  }

  @Test
  public void testMultipleFrench()
  {
    String date;

    date = "15 Avril 2005, 22 Avril 2005";
    assertEquals(2, Core.searchDate(date).size());
    assertEquals(0, Core.searchDate(date).get(0).getPosition());
    assertEquals(15, Core.searchDate(date).get(1).getPosition());

    date = "15 Avril 2005 et 22 Avril 2005";
    assertEquals(2, Core.searchDate(date).size());
    assertEquals(0, Core.searchDate(date).get(0).getPosition());
    assertEquals(17, Core.searchDate(date).get(1).getPosition());

    date = "Allez bouge ! les 15, 16 et 17 April 2005.";
    assertEquals(1, Core.searchDate(date).size());
    assertEquals(28, Core.searchDate(date).get(0).getPosition());
  }
  
  @Test
  public void testNumericalDate()
  {
    assertEquals(1, Core.searchDate("25/01/2009").size());
    assertEquals(1, Core.searchDate("  25/01/2009 ").size());
    assertEquals(1, Core.searchDate("Bonjour nous sommes le 25/01/2009").size());
    assertEquals(1, Core.searchDate("25-01-2009").size());
    assertEquals(1, Core.searchDate("01-25-2009").size());
    assertEquals(1, Core.searchDate("25_01_2009").size());
    
    assertEquals(1, Core.searchDate("25/01/09").size());
    assertEquals(1, Core.searchDate("  25/01/09 ").size());
    assertEquals(1, Core.searchDate("Bonjour nous sommes le 25/01/09").size());
    assertEquals(1, Core.searchDate("25-01-09").size());
    assertEquals(1, Core.searchDate("01-25-09").size());
    assertEquals(1, Core.searchDate("25_01_09").size());
  }
  
  @Test
  public void testSizeResults()
  {
    assertEquals("March 25, 2005", Core.searchDate("March 25, 2005").get(0).getDate());
    assertEquals("15 Jun 2005", Core.searchDate("15 Jun 2005").get(0).getDate());
    assertEquals("5 Avr. 1988", Core.searchDate("5 Avr. 1988").get(0).getDate());
    assertEquals("25/01/09", Core.searchDate("Bonjour nous sommes le 25/01/09").get(0).getDate());
    assertEquals("25/01/1999", Core.searchDate("25/01/1999").get(0).getDate());
    assertEquals("25_01_2009", Core.searchDate("25_01_2009").get(0).getDate());
    assertEquals("5 nov 2005", Core.searchDate("1 5 nov 2005 1").get(0).getDate());
  }

}
