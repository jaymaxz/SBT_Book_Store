import com.google.gson.Gson
import scalaj.http.{Http, HttpOptions}

class AddBookMenu {
  def show() = {
    println()
    println("Add a new book")
    println()
    println("Press Enter Book name and press enter")
    val bookName: String = scala.io.StdIn.readLine()
    val gson: Gson = new Gson()
    var bookNameJson = gson.toJson(bookName)
    val result = Http("http://localhost:8000/addBook").postData(bookNameJson)
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .option(HttpOptions.readTimeout(10000)).asString
    println()
    val book: Book = gson.fromJson(result.body, classOf[Book])
    println("Book ID: "+book.bookID+"\nBook Name: " +book.name)
    println()
    println("Book added successfully")
    println()
    println("Press Enter to go back")
    scala.io.StdIn.readLine()
  }
}




