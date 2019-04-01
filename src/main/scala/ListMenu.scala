import java.util

import com.google.gson.{Gson, JsonArray, JsonParser}
import com.rabbitmq.client.ConnectionFactory
import scalaj.http.Http

class ListMenu {
  /*private var connection: com.rabbitmq.client.Connection = null
  private var channel: com.rabbitmq.client.Channel  = null
  private val requestQueueName = "rpc_queue"*/

  private val QUEUE_NAME = "hello"

  def show() = {
    println()
    println("Book List")
    println()


    val factory = new ConnectionFactory()
    factory.setHost("localhost")
    val connection = factory.newConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(QUEUE_NAME, false, false, false, null)
    val message = "Hello World!"
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"))
    println(" [x] Sent '" + message + "'")
    channel.close()
    connection.close()



    val result = Http("http://localhost:8000/getAllBooks").asString
    val jsonPsr: JsonParser =new JsonParser()
    val jsonAry: JsonArray = jsonPsr.parse(result.body).asInstanceOf[JsonArray]
    val bookList: util.ArrayList[Book] = new util.ArrayList[Book]()
    val gson: Gson = new Gson()
    jsonAry.forEach(jsnObj => bookList.add(gson.fromJson(jsnObj, classOf[Book])))
    bookList.forEach((book: Book)=>println(book.bookID+"."+book.name))
    println()
    println("Press Enter to go back")
    scala.io.StdIn.readLine()
  }


}
