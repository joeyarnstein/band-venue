import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (req, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("template", "templates/bands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands/new", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/newband.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/new", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String bandName = req.queryParams("band-name");
      if (!bandName.equals("")){
        Band newband = new Band(bandName);
        newband.save();
        res.redirect("/bands/"+newband.getId());
      } else {
        res.redirect("/silly");
      }
      return null;
    });

    get("/bands/:id", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Band currentBand = Band.find(Integer.parseInt(req.params("id")));
      model.put("band", currentBand);
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/edit-band", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String editName = req.queryParams("edit-name");
      Band currentBand = Band.find(Integer.parseInt(req.queryParams("band-id")));
      currentBand.updateName(editName);
      res.redirect("/bands/"+currentBand.getId());
      return null;
    });

    post("/add_venue", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String venueName = req.queryParams("venue-name");
      if (!venueName.equals("")){
        Band currentBand = Band.find(Integer.parseInt(req.queryParams("band-id")));
        currentBand.addVenue(venueName);
        res.redirect("/bands/"+currentBand.getId());
      } else {
        res.redirect("/silly");
      }
      return null;
    });

    get("/silly", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/silly.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



  }

}
