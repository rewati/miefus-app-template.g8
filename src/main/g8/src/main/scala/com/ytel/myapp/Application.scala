package com.ytel.minions.myapp

import com.ytel.miefus.{KafkaStreamProcessor, _}

/**
  * Start writing your message handler..
  */
class MyMessageProcessor extends MessageProcessor  {
  override def messageHandler(m: KafkaMessage): Unit = {
    // Handle message
    log.debug(s"New message received: "+m.msg)
  }
}

object Application extends MiefusApplication with KafkaStreamProcessor  {
  override val streamName = "MyStream"
  override val processingActors = List(processor[MyMessageProcessor]("MyMessageProcessor"))
  kafkaStream.initialize
}

