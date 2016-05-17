import java.util.List;
import org.sql2o.*;

public class Venue {
  private String name;
  private int id;

  public Venue(String name){
    this.name = name;
  }
  public String getName(){
    return name;
  }
  public int getId(){
    return id;
  }
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO venues (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }
  public static List<Venue> all(){
    String sql = "SELECT * FROM venues;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Venue.class);
    }
  }
}
