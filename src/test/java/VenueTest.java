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
  public void saveAndGetId_savesToDatabaseAndSavesLocalVenueId_true(){
    Venue testVenue = new Venue("Wonder Ballroom");
    testVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertTrue(testVenue.getId() == savedVenue.getId());
  }
  // @Test
  // public void find_returnsABandFromDatabase_true(){
  //   Band testBandOne = new Band("James Brown");
  //   testBandOne.save();
  //   Band sameBandFromDatabase = Band.find(testBandOne.getId());
  //   assertTrue(testBandOne.getId() == sameBandFromDatabase.getId());
  // }
  // @Test
  // public void updateNam_updatesBandName_true(){
  //   Band marsVolta = new Band("At the Drive-In");
  //   marsVolta.save();
  //   marsVolta.updateName("The Mars Volta");
  //   assertTrue(marsVolta.getName().equals("The Mars Volta"));
  // }
  // @Test
  // public void updateName_updatesBandNameInDatabase_true(){
  //   Band marsVolta = new Band("At the Drive-In");
  //   marsVolta.save();
  //   marsVolta.updateName("The Mars Volta");
  //   assertTrue(Band.find(marsVolta.getId()).getName().equals("The Mars Volta"));
  // }
  // @Test
  // public void delete_updatesBandNameInDatabase_true(){
  //   Band marsVolta = new Band("At the Drive-In");
  //   marsVolta.save();
  //   marsVolta.delete();
  //   assertEquals(0, Band.all().size());
  // }
}
