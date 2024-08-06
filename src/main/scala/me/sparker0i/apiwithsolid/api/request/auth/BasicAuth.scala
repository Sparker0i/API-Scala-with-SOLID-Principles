package me.sparker0i.apiwithsolid.api.request.auth

case class BasicAuth(username: String, password: String) extends ApiAuth {
  override def applyTo(request: AuthRequestProvider): Unit = {
    request.addBasicAuth(username, password)
  }
}