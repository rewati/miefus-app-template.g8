package com.ytel.minions.myapp

import com.ytel.miefus.{KafkaStreamProcessor, _}

object Application extends MiefusApplication with KafkaStreamProcessor  {
  override val streamName = "MyStream"
  override val processingActors = List(processor[MyMessageProcessor]("MyMessageProcessor"))
  kafkaStream.initialize
}

class MyMessageProcessor extends MessageProcessor  {
  override def messageHandler(m: KafkaMessage): Unit = {
    // Handle message
    log.debug(s"New message received: "+m.msg)
  }
}

