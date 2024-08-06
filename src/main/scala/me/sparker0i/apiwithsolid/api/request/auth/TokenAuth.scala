package me.sparker0i.apiwithsolid.api.request.auth

case class TokenAuth(token: String) extends ApiAuth {
  override def applyTo(request: AuthRequestProvider): Unit = {
    request.addBearerToken(token)
  }
}