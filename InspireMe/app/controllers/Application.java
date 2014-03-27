package controllers;

import play.*;
import play.api.templates.Html;
import play.mvc.*;

import views.html.*;
import views.html.index;
import views.html.main;

public class Application extends Controller {

    public static Result index() {
        return ok(main.render("Inspire Me Search", new Html(null)));
    }

    public static Result search() {
        return ok("A search");
    }

}
