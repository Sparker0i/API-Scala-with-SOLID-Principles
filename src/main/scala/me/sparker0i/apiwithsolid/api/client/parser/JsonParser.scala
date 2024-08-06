package me.sparker0i.apiwithsolid.api.client.parser

import me.sparker0i.apiwithsolid.api.request.body.{ApiBody, JsonBody}

case class JsonParser(content: Array[Byte]) extends BodyParser {
  override def parseBody(): ApiBody = JsonBody(new String(content, "UTF-8"))
}
