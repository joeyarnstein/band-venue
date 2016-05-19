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
    // start start start start -----------------

     get("/venues", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("venues", Venue.all());
      model.put("template", "templates/venues.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/venues/new", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/newvenue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venues/new", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String venueName = req.queryParams("venue-name");
      if (!venueName.equals("")){
        Venue newvenue = new Venue(venueName);
        newvenue.save();
        res.redirect("/venues/"+newvenue.getId());
      } else {
        res.redirect("/silly");
      }
      return null;
    });
    get("/venues/:id", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Venue currentVenue = Venue.find(Integer.parseInt(req.params("id")));
      model.put("venue", currentVenue);
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
// stop stop stop stop --------------------------


    post("/bands/edit-band", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String editName = req.queryParams("edit-name");
      Band currentBand = Band.find(Integer.parseInt(req.queryParams("band-id")));
      currentBand.updateName(editName);
      res.redirect("/bands/"+currentBand.getId());
      return null;
    });

    get("/bands/:id/delete", (req,res) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Band currentBand = Band.find(Integer.parseInt(req.params("id")));
      currentBand.delete();
      res.redirect("/bands");
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
