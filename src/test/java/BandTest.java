import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void band_isInstanceofBand_true(){
    Band testBand = new Band("Iron Maiden");
    assertTrue(testBand instanceof Band);
  }
  @Test
  public void getName_returnsNameOfBand_true(){
    Band testBand = new Band("Iron Maiden");
    assertTrue(testBand.getName().equals("Iron Maiden"));
  }
  @Test
  public void saveAndAll_savesToDatabaseAndReceivesId_true(){
    Band testBand = new Band("Karnivool");
    testBand.save();
    Band savedBand = Band.all().get(0);
    assertTrue(testBand.getId() == savedBand.getId());
  }
  @Test
  public void find_returnsABandFromDatabase_true(){
    Band testBandOne = new Band("James Brown");
    testBandOne.save();
    Band sameBandFromDatabase = Band.find(testBandOne.getId());
    assertTrue(testBandOne.getId() == sameBandFromDatabase.getId());
  }
}
