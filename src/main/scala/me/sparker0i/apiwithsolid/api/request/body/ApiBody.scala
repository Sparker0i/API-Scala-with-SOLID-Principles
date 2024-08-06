package me.sparker0i.apiwithsolid.api.request.body

trait ApiBody {
  def contentType: String
  def content: Array[Byte]
}