package formatters.json

import io.swagger.annotations.{ApiModel, ApiModelProperty}
import org.joda.time.DateTime
import play.api.libs.json.{JsObject, Json, OWrites}

@ApiModel(description = "Token object")
case class Token(
                @ApiModelProperty(value = "token value", readOnly = true) token: String,
                @ApiModelProperty(value = "expiry date", readOnly = true) expiresOn: DateTime
                )

object Token {
  implicit object TokenWrites extends OWrites[Token] {
    def writes(token: Token): JsObject = {
      val json = Json.obj(
        "token" -> token.token,
        "expiresOn" -> token.expiresOn.toString
      )
      json
    }
  }
}
