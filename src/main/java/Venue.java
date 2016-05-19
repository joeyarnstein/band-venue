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
  public static Venue find(int id){
    String sql = "SELECT * FROM venues WHERE id=:id;";
    try(Connection con = DB.sql2o.open()) {
      Venue fetchedVenue = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Venue.class);
      return fetchedVenue;
    }
  }
  public List<Band> getBands() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT bands.* FROM venues JOIN bands_venues ON (venues.id = bands_venues.venue_id) JOIN bands ON (bands_venues.band_id = bands.id) WHERE venues.id =:id;";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Band.class);
    }
  }
}
