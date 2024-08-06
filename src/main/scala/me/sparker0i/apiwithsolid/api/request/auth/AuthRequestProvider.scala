package me.sparker0i.apiwithsolid.api.request.auth

trait AuthRequestProvider {
  def addBearerToken(token: String): Unit
  def addBasicAuth(username: String, password: String): Unit
}
