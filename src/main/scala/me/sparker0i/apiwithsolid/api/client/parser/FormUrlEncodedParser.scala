package me.sparker0i.apiwithsolid.api.client.parser

import me.sparker0i.apiwithsolid.api.request.body.{ApiBody, FormUrlEncodedBody, JsonBody}

case class FormUrlEncodedParser(content: Array[Byte]) extends BodyParser {
  override def parseBody(): ApiBody = {
    val decodedContent = new String(content, "UTF-8")
    val formData = decodedContent.split("&").map { pair =>
      val Array(key, value) = pair.split("=")
      java.net.URLDecoder.decode(key, "UTF-8") -> java.net.URLDecoder.decode(value, "UTF-8")
    }.toMap
    FormUrlEncodedBody(formData)
  }
}

