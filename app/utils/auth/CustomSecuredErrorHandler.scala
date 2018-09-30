package utils.auth

import com.mohiva.play.silhouette.api.actions.SecuredErrorHandler
import javax.inject.Inject
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.mvc.Results._

import scala.concurrent.Future

class CustomSecuredErrorHandler @Inject()(val messagesApi: MessagesApi)
  extends SecuredErrorHandler with I18nSupport with RequestExtractors with Rendering{

  /**
    * called when a user is not authenticated
    * @param request
    * @return
    */
  override def onNotAuthenticated(implicit request: RequestHeader) =
    produceResponse(Unauthorized, Messages("silhouette.not.authenticated"))

  /**
    * called when a user is authenticated but not authorized
    * @param request
    * @return
    */
  override def onNotAuthorized(implicit request: RequestHeader) =
    produceResponse(Unauthorized, Messages("silhouette.access.denied"))

  protected def produceResponse[S <: Status](status: S, msg: String)(implicit request: RequestHeader): Future[Result] =
    Future.successful(render {
      case Accepts.Json() => status(toJsonError(msg))
    })

  protected def toJsonError(message: String) =
    Json.obj("message" -> message)
}
