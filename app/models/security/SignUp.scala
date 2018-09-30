package models.security

import io.swagger.annotations.{ApiModel, ApiModelProperty}


@ApiModel(description = "SignUp object")
case class SignUp(
                 @ApiModelProperty(value = "user unique identifier", required = true, example = "Lucky") identifier: String,
                 @ApiModelProperty(required = true, example = "this!Password!Is!Very!Very!Strong") password: String,
                 @ApiModelProperty(value = "e-mail address", required = true, example = "admin@admin.com") email: String,
                 @ApiModelProperty(value = "user first name", required = true, example = "Lucky") firstName: String,
                 @ApiModelProperty(value = "user last name", required = true, example = "Lemond") lastName: String
                 )
