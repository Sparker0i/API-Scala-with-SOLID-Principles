package me.sparker0i.apiwithsolid.api.client.parser

import me.sparker0i.apiwithsolid.api.request.body.{ApiBody, JsonBody, XmlBody}

case class XmlParser(content: Array[Byte]) extends BodyParser {
  override def parseBody(): ApiBody = XmlBody(new String(content, "UTF-8"))
}