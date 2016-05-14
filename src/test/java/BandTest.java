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
}
