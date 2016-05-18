import org.sql2o.*;
import org.junit.*; // for @Before and @After
import static org.assertj.core.api.Assertions.assertThat;

import org.fluentlenium.adapter.FluentTest;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Band Tracker");
  }

  @Test
  public void bandIsCreatedandRoutedTest() {
    goTo("http://localhost:4567/newband");
    fill("#band-name").with("James Brown");
    submit(".btn");
    assertThat(pageSource()).contains("James Brown");
  }

  @Test
  public void bandsPageDisplaysAllBands() {
    Band firstBand = new Band("Death");
    Band secondBand = new Band("Life");
    firstBand.save();
    secondBand.save();
    goTo("http://localhost:4567/");
    click("a", withText("here"));
    assertThat(pageSource()).contains("Death");
    assertThat(pageSource()).contains("Life");
  }

}
