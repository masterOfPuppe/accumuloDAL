package model

final class Event(val id:String,
                  val senderActor: Actor,
                  val receiverActor: Actor,
                  val timestamp: Long,
                  val location: String,
                  val payload: Map[String,String])