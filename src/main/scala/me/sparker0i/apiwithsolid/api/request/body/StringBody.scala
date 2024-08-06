package me.sparker0i.apiwithsolid.api.request.body

case class StringBody(text: String) extends ApiBody {
  override def contentType: String = "application/text"
  override def content: Array[Byte] = text.getBytes("UTF-8")
}
