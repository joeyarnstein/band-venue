import java.util.List;
import org.sql2o.*;

public class Band {
  private String name;
  private int id;

  public Band(String name){
    this.name = name;
  }
  public String getName(){
    return name;
  }
  public int getId(){
    return id;
  }

}
