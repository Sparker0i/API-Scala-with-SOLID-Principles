package me.sparker0i.apiwithsolid.api.request.body

case class XmlBody(xml: String) extends ApiBody {
  override def contentType: String = "application/xml"
  override def content: Array[Byte] = xml.getBytes("UTF-8")
}
