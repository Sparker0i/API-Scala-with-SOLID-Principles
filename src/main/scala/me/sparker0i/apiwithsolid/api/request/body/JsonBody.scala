package me.sparker0i.apiwithsolid.api.request.body

case class JsonBody(json: String) extends ApiBody {
  override def contentType: String = "application/json"
  override def content: Array[Byte] = json.getBytes("UTF-8")
}
