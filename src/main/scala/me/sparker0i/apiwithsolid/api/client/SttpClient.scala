package me.sparker0i.apiwithsolid.api.client

import me.sparker0i.apiwithsolid.api.client.parser.BodyParser
import me.sparker0i.apiwithsolid.api.request.auth.AuthRequestProvider
import me.sparker0i.apiwithsolid.api.request.body.JsonBody
import me.sparker0i.apiwithsolid.api.request.{ApiRequest, ApiResponse}
import sttp.client4.{Response, UriContext, asByteArray}
import sttp.client4.httpurlconnection.HttpURLConnectionBackend

import java.net.{HttpURLConnection, URI, URISyntaxException, URL}
import java.security.cert.X509Certificate
import javax.net.ssl.{HttpsURLConnection, SSLContext, TrustManager, X509TrustManager}

protected[api] class SttpClient extends ApiClient {
  private class SttpRequestProviderWrapper(var request: sttp.client4.Request[Either[String, String]]) extends AuthRequestProvider {
    override def addBearerToken(token: String): Unit = {
      request = request.auth.bearer(token)
    }

    override def addBasicAuth(username: String, password: String): Unit = {
      request = request.auth.basic(username, password)
    }
  }

  val trustAllCerts: Array[TrustManager] = Array(new X509TrustManager {
    def getAcceptedIssuers: Array[X509Certificate] = Array()
    def checkClientTrusted(certs: Array[X509Certificate], authType: String): Unit = {}
    def checkServerTrusted(certs: Array[X509Certificate], authType: String): Unit = {}
  })

  val sslContext: SSLContext = SSLContext.getInstance("SSL")
  sslContext.init(null, trustAllCerts, new java.security.SecureRandom())

  def useSSL(conn: HttpURLConnection): Unit = conn match {
    case https: HttpsURLConnection => https.setSSLSocketFactory(sslContext.getSocketFactory)
    case _ => ()
  }

  private val backend = HttpURLConnectionBackend(customizeConnection = useSSL)

  override def send(request: ApiRequest): Either[ApiResponse, ApiResponse] = {
    try {
      new URI(request.url).toURL
    }
    catch {
      case e: URISyntaxException => return Left(ApiResponse(500, Some(JsonBody(ujson.Obj("message" -> e.getMessage).toString())), Map()))
      case e: IllegalArgumentException => return Left(ApiResponse(500, Some(JsonBody(ujson.Obj("message" -> e.getMessage).toString())), Map()))
    }

    var sttpRequest = sttp.client4.basicRequest.method(sttp.model.Method(request.method.toString), uri"${request.url}")

    request.headers.foreach { case (key, value) =>
      sttpRequest = sttpRequest.header(key, value)
    }

    request.body.foreach { body =>
      sttpRequest = sttpRequest.body(body.content).contentType(body.contentType)
    }

    val authRequest = new SttpRequestProviderWrapper(sttpRequest)
    request.authentication.foreach(_.applyTo(authRequest))
    sttpRequest = authRequest.request

    val response = sttpRequest.response(asByteArray).send(backend)
    val headers = response.headers.map(h => h.name -> h.value).toMap
    val responseEntity = ApiResponse(
      response.code.code,
      response.body match {
        case Left(error) => BodyParser.getParser(error.getBytes("UTF-8"), headers) match {
          case Some(value) => Some(value.parseBody())
          case None => None
        }
        case Right(value) =>
          println(new String(value))
          println(headers)
          BodyParser.getParser(value, headers) match {
            case Some(value) => Some(value.parseBody())
            case None => None
          }
      },
      headers
    )

    if (response.code.isClientError || response.code.isServerError) Left(responseEntity)
    else Right(responseEntity)
  }
}
