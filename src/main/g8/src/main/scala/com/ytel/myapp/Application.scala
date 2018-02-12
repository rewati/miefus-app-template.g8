package com.ytel.minions.myapp

import akka.actor.{ActorRef, Props}
import akka.routing.BalancingPool
import com.ytel.miefus._
import com.ytel.miefus.CaseSpec._
import com.ytel.miefus.persistence.KafkaPusher
import com.ytel.miefus.persistence.cassandra.{Cassandra, CassandraException, CassandraQueryStatement, CassandraSuccess}

/**
  * Created by rewatiraman.
  */
object Application extends MiefusApplication with KafkaStreamProcessor  {
  override val streamName = "MyStream"
  lazy val processor: ActorRef =
    ClusterCommon.system.actorOf(BalancingPool(100).props(Props[MyMessageActor]), "MyMessageActor")
  override val processingActors = List(processor)
  kafkaStream.initialize
}

class MyMessageActor extends CommonActor {
  val errorTopic = Configuration("error_topics","myapp_error_topic")
  override def receive = {
    case x: KafkaMessage =>
      log.info(s"Message Received by MyMessageActor: "+x.msg)
    case x =>
      log.error(s"Dont know what to do with message: "+x)
  }
}

