import com.google.gson.Gson
import com.rabbitmq.client.{ConnectionFactory, DeliverCallback}

class AddBookMenu {
  private val ADD_REQ_QUEUE_NAME = "Add Request Queue"
  private val ADD_RES_QUEUE_NAME = "Add Response Queue"
  def show() = {
    println()
    println("Add a new book")
    println()
    println("Press Enter Book name and press enter")
    val bookName: String = scala.io.StdIn.readLine()
    val gson: Gson = new Gson()
    var bookNameJson = gson.toJson(bookName)
    val factory = new ConnectionFactory()
    factory.setHost("localhost")
    val connection = factory.newConnection()
    val channel = connection.createChannel()

    channel.queueDeclare(ADD_RES_QUEUE_NAME, false, false, false, null)
    //println(" [*] Waiting for messages. To exit press CTRL+C")
    var response = ""
    val deliverCallback: DeliverCallback = (_, delivery) => {
      response = new String(delivery.getBody, "UTF-8")
      //println(" [x] Received '" + response + "'")
      val book: Book = gson.fromJson(response, classOf[Book])
      println("Book ID: "+book.bookID+"\nBook Name: " +book.name)
      println()
      println("Book added successfully")
      println()
      println("Press Enter to go back")
    }

    channel.basicConsume(ADD_RES_QUEUE_NAME, true, deliverCallback, _ => { })
    val message = bookNameJson
    channel.queueDeclare(ADD_REQ_QUEUE_NAME, false, false, false, null)
    channel.basicPublish("", ADD_REQ_QUEUE_NAME, null, message.getBytes("UTF-8"))
    //println(" [x] Sent '" + message + "'")
    scala.io.StdIn.readLine()

    /*val result = Http("http://localhost:8000/addBook").postData(bookNameJson)
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .option(HttpOptions.readTimeout(10000)).asString*/
  }
}




