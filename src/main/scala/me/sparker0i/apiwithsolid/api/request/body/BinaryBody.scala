package me.sparker0i.apiwithsolid.api.request.body

case class BinaryBody(data: Array[Byte], _contentType: String) extends ApiBody {
  override def contentType: String = _contentType
  override def content: Array[Byte] = data
}