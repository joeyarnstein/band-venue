import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void venue_isInstanceofVenue_true(){
    Venue testVenue = new Venue("Wonder Ballroom");
    assertTrue(testVenue instanceof Venue);
  }
  @Test
  public void getName_returnsNameOfVenue_true(){
    Venue testVenue = new Venue("Wonder Ballroom");
    assertTrue(testVenue.getName().equals("Wonder Ballroom"));
  }

  @Test
  public void all_returnsListOfAllInstancesOfVenue(){
    Venue oneVenue = new Venue("Wonder Ballroom");
    oneVenue.save();
    Venue twoVenue = new Venue("Crystal Ballroom");
    twoVenue.save();
    Venue threeVenue = new Venue("Crystal Ballroom");
    threeVenue.save();
    assertEquals(3, Venue.all().size());
  }

  @Test
  public void save_savesToDatabaseAndSavesLocalVenue_true(){
    Venue testVenue = new Venue("Crystal");
    testVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertTrue(testVenue.getId() == savedVenue.getId());
  }
}
