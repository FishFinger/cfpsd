package app;

public class WeightedDate implements Comparable<WeightedDate>
{
  private float _weight;
  private String _date;
  
  public WeightedDate(float weight, String date)
  {
    this._weight = weight;
    this._date = date;
  }

  /**
   * @return the _weight
   */
  public float getWeight()
  {
    return _weight;
  }

  /**
   * @return the _date
   */
  public String getDate()
  {
    return _date;
  }

  /**
   * @param _weight the _weight to set
   */
  public void setWeight(float weight)
  {
    this._weight = weight;
  }

  /**
   * @param _date the _date to set
   */
  public void setDate(String date)
  {
    this._date = date;
  }

  @Override
  public int compareTo(WeightedDate d)
  {
    // TODO Auto-generated method stub
    return Float.compare(this._weight, d._weight);
  }
  
  public String toString()
  {
    return "("+this._weight+")"+ this._date;
  }
}
