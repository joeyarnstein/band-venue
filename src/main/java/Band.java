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

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Band> all(){
    String sql = "SELECT * FROM bands;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }

  public static Band find(int id){
    String sql = "SELECT * FROM bands WHERE id=:id;";
    try(Connection con = DB.sql2o.open()) {
      Band fetchedBand = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Band.class);
      return fetchedBand;
    }
  }

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE bands SET name = :name WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
    this.name = name;
  }


  public void delete() {
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM bands WHERE id=:id;"
      //+" DELETE FROM bands_venues WHERE band_id=:id;"
      ;
      con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public void addVenue(String venue) {
    Integer venue_id;
    Integer relationshipAlreadyExistsChecker;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id FROM venues WHERE name=:name;";
      venue_id = con.createQuery(sql)
        .addParameter("name", venue)
        .executeAndFetchFirst(Integer.class);
    }
    if (venue_id != null) {

      try(Connection con = DB.sql2o.open()){
        String sql = "SELECT id FROM bands_venues WHERE venue_id=:venue_id AND band_id=:band_id;";
        relationshipAlreadyExistsChecker = con.createQuery(sql)
          .addParameter("venue_id", venue_id)
          .addParameter("band_id", this.id)
          .executeAndFetchFirst(Integer.class);
      }

      if (relationshipAlreadyExistsChecker == null) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id);";
          con.createQuery(sql)
            .addParameter("venue_id", venue_id)
            .addParameter("band_id", this.getId())
            .executeUpdate();
        }
      }

    } else {
      Venue newVenue = new Venue(venue);
      newVenue.save();
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO bands_venues (venue_id, band_id) VALUES (:venue_id, :band_id);";
        con.createQuery(sql)
          .addParameter("venue_id", newVenue.getId())
          .addParameter("band_id", this.getId())
          .executeUpdate();
      }
    }
  }

}
