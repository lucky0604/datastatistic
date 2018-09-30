package controllers

import com.mohiva.play.silhouette.api.Silhouette
import io.swagger.annotations.{Api, ApiOperation}
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import utils.auth.DefaultEnv

import scala.concurrent.Future


@Api(value = "example data")
@Singleton
class ApplicationController @Inject()(components: ControllerComponents,
                                      silhouette: Silhouette[DefaultEnv]) extends AbstractController(components) {

  @ApiOperation(value = "", hidden = true)
  def index = Action {
    Ok(views.html.index("Your new application is ready"))
  }

  @ApiOperation(value = "", hidden = true)
  def redirectDocs = Action { implicit request =>
    Redirect(
      url = "/assets/lib/swagger-ui/index.html",
      queryString = Map("url" -> Seq("http://" + request.host + "/swagger.json"))
    )
  }

  @ApiOperation(value = "Get bad password value")
  def badPassword = silhouette.SecuredAction.async { implicit request =>
    Future.successful(Ok(Json.obj("result" -> "qwery1234")))
  }

  @ApiOperation(value = "Get colors")
  def colors = Action.async {
    Future.successful(Ok(Json.arr("black", "blue", "green", "red", "white")))
  }

}
