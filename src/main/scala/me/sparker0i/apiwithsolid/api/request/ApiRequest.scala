package me.sparker0i.apiwithsolid.api.request

import me.sparker0i.apiwithsolid.api.request.auth.ApiAuth
import me.sparker0i.apiwithsolid.api.request.body.ApiBody
import me.sparker0i.apiwithsolid.api.request.method.ApiMethod

protected[api] case class ApiRequest(
                        method: ApiMethod,
                        url: String,
                        body: Option[ApiBody] = None,
                        headers: Map[String, String] = Map.empty,
                        authentication: Option[ApiAuth] = None
                      )