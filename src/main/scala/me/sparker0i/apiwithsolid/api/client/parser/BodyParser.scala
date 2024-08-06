package me.sparker0i.apiwithsolid.api.client.parser

import me.sparker0i.apiwithsolid.api.request.body.ApiBody

trait BodyParser {
  def parseBody(): ApiBody
}

object BodyParser {
  def getParser(content: Array[Byte], headers: Map[String, String]): Option[BodyParser] = headers.get("Content-Type") match {
    case Some(value) => valueMatcher(content, value)
    case None => headers.get("Content-Type") match {
      case Some(value) => valueMatcher(content, value)
      case None => None
    }
  }

  def valueMatcher(content: Array[Byte], value: String): Option[BodyParser] = value match {
    case "application/json" => Some(JsonParser(content))
    case "application/xml" => Some(XmlParser(content))
    case "application/x-www-form-urlencoded" => Some(FormUrlEncodedParser(content))
    case "application/text" => Some(StringParser(content))
    case e => Some(BinaryParser(content, e))
  }
}
