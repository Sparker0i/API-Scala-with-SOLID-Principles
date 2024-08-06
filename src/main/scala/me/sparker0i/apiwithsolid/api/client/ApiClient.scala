package me.sparker0i.apiwithsolid.api.client

import me.sparker0i.apiwithsolid.api.request._

trait ApiClient {
  def send(request: ApiRequest): Either[ApiResponse, ApiResponse]
}

object ApiClient {
  def apply() = new SttpClient()
}