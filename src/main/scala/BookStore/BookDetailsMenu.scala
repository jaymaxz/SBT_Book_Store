package BookStore

import com.google.gson.Gson
import com.rabbitmq.client.{ConnectionFactory, DeliverCallback}

class BookDetailsMenu {
  private val DETAILS_REQ_QUEUE_NAME = "Details Request Queue"
  private val DETAILS_RES_QUEUE_NAME = "Details Response Queue"
  var BookName: String = ""

  def show() = {
    println()
    println("BookStore.Book Details")
    println()
    println("Press Enter BookStore.Book ID number and press enter")
    val bookID: Int = scala.io.StdIn.readInt()
    val gson: Gson = new Gson()
    val index = gson.toJson(bookID)

    val factory = new ConnectionFactory()
    factory.setHost("localhost")
    val connection = factory.newConnection()
    val channel = connection.createChannel()

    channel.queueDeclare(DETAILS_RES_QUEUE_NAME, false, false, false, null)
    //println(" [*] Waiting for messages. To exit press CTRL+C")
    var response = ""
    val deliverCallback: DeliverCallback = (_, delivery) => {
      response = new String(delivery.getBody, "UTF-8")
      //println(" [x] Received '" + response + "'")
      val book: Book = gson.fromJson(response, classOf[Book])
      println()
      BookName = book.name
      println("BookStore.Book ID: "+book.bookID+"\nBookStore.Book Name: " +book.name)
      println()
      println("Press Enter to go back")
    }

    channel.basicConsume(DETAILS_RES_QUEUE_NAME, true, deliverCallback, _ => { })
    val message = index
    channel.queueDeclare(DETAILS_REQ_QUEUE_NAME, false, false, false, null)
    channel.basicPublish("", DETAILS_REQ_QUEUE_NAME, null, message.getBytes("UTF-8"))
    //println(" [x] Sent '" + message + "'")
    scala.io.StdIn.readLine()

    /*val result = Http("http://localhost:8000/getBookDetails").postData(index)
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .option(HttpOptions.readTimeout(10000)).asString
    val book: BookStore.Book = gson.fromJson(result.body, classOf[BookStore.Book])*/

  }
}
