package me.sparker0i.apiwithsolid.api.request.auth

trait ApiAuth {
  def applyTo(request: AuthRequestProvider): Unit
}
