package me.sparker0i.apiwithsolid.api.client.parser

import me.sparker0i.apiwithsolid.api.request.body.{ApiBody, BinaryBody}

case class BinaryParser(content: Array[Byte], contentType: String) extends BodyParser {
  override def parseBody(): ApiBody = BinaryBody(content, contentType)
}
