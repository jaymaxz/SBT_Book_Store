import java.util

import com.google.gson.{Gson, JsonArray, JsonParser}
import com.rabbitmq.client.{ConnectionFactory, DeliverCallback}

class ListMenu {
  private val LIST_REQ_QUEUE_NAME = "List Request Queue"
  private val LIST_RES_QUEUE_NAME = "List Response Queue"

  def show() = {
    println()
    println("Book List")
    println()

    val factory = new ConnectionFactory()
    factory.setHost("localhost")
    val connection = factory.newConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(LIST_REQ_QUEUE_NAME, false, false, false, null)
    val message = "Book List Request"
    channel.basicPublish("", LIST_REQ_QUEUE_NAME, null, message.getBytes("UTF-8"))
    //println(" [x] Sent '" + message + "'")

    channel.queueDeclare(LIST_RES_QUEUE_NAME, false, false, false, null)
    //println(" [*] Waiting for messages. To exit press CTRL+C")
    var response = "";
    val deliverCallback: DeliverCallback = (_, delivery) => {
      response = new String(delivery.getBody, "UTF-8")
      //println(" [x] Received '" + response + "'")
    }
    channel.basicConsume(LIST_RES_QUEUE_NAME, true, deliverCallback, _ => { })

    //val result = Http("http://localhost:8000/getAllBooks").asString
    val jsonPsr: JsonParser =new JsonParser()
    //val jsonAry: JsonArray = jsonPsr.parse(result.body).asInstanceOf[JsonArray]
    val jsonAry: JsonArray = jsonPsr.parse(response).asInstanceOf[JsonArray]
    val bookList: util.ArrayList[Book] = new util.ArrayList[Book]()
    val gson: Gson = new Gson()
    jsonAry.forEach(jsnObj => bookList.add(gson.fromJson(jsnObj, classOf[Book])))
    bookList.forEach((book: Book)=>println(book.bookID+"."+book.name))
    println()
    println("Press Enter to go back")
    scala.io.StdIn.readLine()
  }


}
