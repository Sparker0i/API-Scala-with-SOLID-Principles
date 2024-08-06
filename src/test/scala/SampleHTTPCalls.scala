import me.sparker0i.apiwithsolid.api.client.ApiClient
import me.sparker0i.apiwithsolid.api.request.ApiRequestBuilder
import me.sparker0i.apiwithsolid.api.request.body.*
import me.sparker0i.apiwithsolid.api.request.method.*
import org.scalatest.funsuite.AnyFunSuite

class SampleHTTPCalls extends AnyFunSuite {
  test("Sample GET") {
    val url = "https://reqres.in/api/users?page=2"
    val request = ApiRequestBuilder()
      .withUrl(url)
      .withMethod(GET)
      .build()

    val response = ApiClient().send(request)
    assert(response.isRight)

    response match
      case Left(value) => // Won't reach here as long as the API is up
      case Right(value) => assert(ujson.read(new String(value.body.get.content, "UTF-8"))("data").arr.nonEmpty)
  }

  test("Sample POST") {
    val url = "https://reqres.in/api/users"
    val request = ApiRequestBuilder()
      .withUrl(url)
      .withMethod(POST)
      .withBody(JsonBody("""{"name": "Ben Stokes","job": "leader"}"""))
      .build()

    val response = ApiClient().send(request)
    assert(response.isRight)

    response match
      case Left(value) => // Won't reach here
      case Right(value) =>
        assert(ujson.read(new String(value.body.get.content, "UTF-8"))("name").str == "Ben Stokes")
        assert(ujson.read(new String(value.body.get.content, "UTF-8"))("createdAt").str.nonEmpty)
  }

  test("Sample PUT") {
    val url = "https://reqres.in/api/users/2"
    val request = ApiRequestBuilder()
      .withUrl(url)
      .withMethod(PUT)
      .withBody(JsonBody("""{"name": "Morpheus","job": "vampire"}"""))
      .build()

    val response = ApiClient().send(request)
    assert(response.isRight)

    response match
      case Left(value) => // Won't reach here
      case Right(value) =>
        assert(ujson.read(new String(value.body.get.content, "UTF-8"))("name").str == "Morpheus")
        assert(ujson.read(new String(value.body.get.content, "UTF-8"))("updatedAt").str.nonEmpty)
  }

  test("Sample DELETE") {
    val url = "https://reqres.in/api/users/2"
    val request = ApiRequestBuilder()
      .withUrl(url)
      .withMethod(DELETE)
      .withBody(JsonBody("""{"name": "Morpheus","job": "vampire"}"""))
      .build()

    val response = ApiClient().send(request)
    assert(response.isRight)

    response match {
      case Left(value) => 
      case Right(value) => assert(value.code == 204)
    }
  }
}
