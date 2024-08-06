package me.sparker0i.apiwithsolid.api.request

import me.sparker0i.apiwithsolid.api.request.body.ApiBody

case class ApiResponse(code: Int, body: Option[ApiBody], headers: Map[String, String] = Map())
