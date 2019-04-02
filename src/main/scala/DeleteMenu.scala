import com.google.gson.Gson
import com.rabbitmq.client.{ConnectionFactory, DeliverCallback}

class DeleteMenu {
  private val DELETE_REQ_QUEUE_NAME = "Add Request Queue"
  private val DELETE_RES_QUEUE_NAME = "Add Response Queue"
  def show() = {
    println()
    println("Delete a Book")
    println()
    println("Press Enter Book ID number and press enter")
    val bookID: Int = scala.io.StdIn.readInt()
    val gson: Gson = new Gson()
    vaL index = gson.toJson(bookID)
    val factory = new ConnectionFactory()
    factory.setHost("localhost")
    val connection = factory.newConnection()
    val channel = connection.createChannel()

    channel.queueDeclare(DELETE_RES_QUEUE_NAME, false, false, false, null)
    //println(" [*] Waiting for messages. To exit press CTRL+C")
    var response = ""
    val deliverCallback: DeliverCallback = (_, delivery) => {
      response = new String(delivery.getBody, "UTF-8")
      //println(" [x] Received '" + response + "'")
      val book: Book = gson.fromJson(response, classOf[Book])
      println()
      println("Book ID: " + book.bookID + "\nBook Name: " + book.name)
      println()
      println("Book deleted successfully")
      println()
      println("Press Enter to go back")
    }

    channel.basicConsume(DELETE_RES_QUEUE_NAME, true, deliverCallback, _ => { })
    val message = index
    channel.queueDeclare(DELETE_REQ_QUEUE_NAME, false, false, false, null)
    channel.basicPublish("", DELETE_REQ_QUEUE_NAME, null, message.getBytes("UTF-8"))
    //println(" [x] Sent '" + message + "'")
    scala.io.StdIn.readLine()
    /*val result = Http("http://localhost:8000/deleteBook").postData(index)
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .option(HttpOptions.readTimeout(10000)).asString*/
  }
}
