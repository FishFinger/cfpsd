package app;

public class DateWithPosition
{
  private int _position;
  private String _date;
  
  public DateWithPosition(int position, String date)
  {
    this._position = position;
    this._date = date;
  }

  /**
   * @return the _position
   */
  public int getPosition()
  {
    return _position;
  }

  /**
   * @return the _date
   */
  public String getDate()
  {
    return _date;
  }

  /**
   * @param _position the _position to set
   */
  public void setPosition(int position)
  {
    this._position = position;
  }

  /**
   * @param _date the _date to set
   */
  public void setDate(String date)
  {
    this._date = date;
  }
  
  


}
