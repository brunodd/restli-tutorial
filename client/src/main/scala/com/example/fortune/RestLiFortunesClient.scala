package com.example.fortune

import java.util.Collections

import com.linkedin.r2.transport.common.bridge.client.TransportClientAdapter
import com.linkedin.r2.transport.http.client.HttpClientFactory
import com.linkedin.r2.transport.http.client.HttpClientFactory.Builder
import com.linkedin.restli.client.{GetRequest, Response, ResponseFuture, RestClient}

object RestLiFortunesClient {

  def main(args: Array[String]): Unit = {

    val http: HttpClientFactory = new Builder().build()
    val r2Client = new TransportClientAdapter(
      http.getClient(Collections.emptyMap[String, String]())
    )
    val restClient = new RestClient(r2Client, "http://localhost:8080/")
    // Generate a random ID for a fortune cookie, in the range 0 - 5
    val fortuneId: Long = (Math.random() * 5).toLong

    val fortunesBuilders: FortunesRequestBuilders =
      new FortunesRequestBuilders()
    val getBuilder: FortunesGetRequestBuilder = fortunesBuilders.get
    val getRequest: GetRequest[Fortune] = getBuilder.id(fortuneId).build

    val getFuture: ResponseFuture[Fortune] = restClient.sendRequest(getRequest)
    val response: Response[Fortune] = getFuture.getResponse

    val message: Fortune = response.getEntity

    println(s"Received message ${message.getFortune}")
  }
}
