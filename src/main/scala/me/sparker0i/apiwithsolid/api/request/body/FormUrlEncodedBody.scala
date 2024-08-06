package me.sparker0i.apiwithsolid.api.request.body

case class FormUrlEncodedBody(formData: Map[String, String]) extends ApiBody {
  override def contentType: String = "application/x-www-form-urlencoded"
  override def content: Array[Byte] = formData
    .map(e => s"${java.net.URLEncoder.encode(e._1, "UTF-8")}=${java.net.URLEncoder.encode(e._2, "UTF-8")}")
    .mkString("&")
    .getBytes("UTF-8")
}
