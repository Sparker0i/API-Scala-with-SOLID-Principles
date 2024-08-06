package me.sparker0i.apiwithsolid.api.request

import me.sparker0i.apiwithsolid.api
import me.sparker0i.apiwithsolid.api.request.ApiRequest
import me.sparker0i.apiwithsolid.api.request.auth.ApiAuth
import me.sparker0i.apiwithsolid.api.request.body.ApiBody
import me.sparker0i.apiwithsolid.api.request.method.ApiMethod

case class ApiRequestBuilder(
                               private val method: Option[ApiMethod] = None,
                               private val url: String = "",
                               private val body: Option[ApiBody] = None,
                               private val headers: Map[String, String] = Map(),
                               private val authentication: Option[ApiAuth] = None
                             ) {
  def withMethod(method: ApiMethod): ApiRequestBuilder = this.copy(method = Some(method))

  def withUrl(url: String): ApiRequestBuilder = this.copy(url = url)

  def withBody(body: ApiBody): ApiRequestBuilder = this.copy(body = Some(body))

  def withHeaders(headers: Map[String, String]): ApiRequestBuilder = this.copy(headers = headers)

  def addHeader(key: String, value: String): ApiRequestBuilder = this.copy(headers = headers + (key -> value))

  def withAuthentication(auth: ApiAuth): ApiRequestBuilder = this.copy(authentication = Some(auth))

  def build(): ApiRequest = {
    if (method.isEmpty || url.isEmpty) throw new IllegalStateException("Method and URL cannot be empty")
    ApiRequest(method.get, url, body, headers, authentication)
  }
}
