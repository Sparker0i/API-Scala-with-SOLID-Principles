package me.sparker0i.apiwithsolid.api.client.parser

import me.sparker0i.apiwithsolid.api.request.body.{ApiBody, StringBody}

case class StringParser(content: Array[Byte]) extends BodyParser {
  override def parseBody(): ApiBody = StringBody(new String(content, "UTF-8"))
}
