import com.google.gson.Gson
import scalaj.http.{Http, HttpOptions}

class DeleteMenu {
  def show() = {
    println()
    println("Delete a Book")
    println()
    println("Press Enter Book ID number and press enter")
    val bookID: Int = scala.io.StdIn.readInt()
    val gson: Gson = new Gson()
    var index = gson.toJson(bookID)
    val result = Http("http://localhost:8000/deleteBook").postData(index)
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .option(HttpOptions.readTimeout(10000)).asString
    val book: Book = gson.fromJson(result.body, classOf[Book])
    println()
    println("Book ID: " + book.bookID + "\nBook Name: " + book.name)
    println()
    println("Book deleted successfully")
    println()
    println("Press Enter to go back")
    scala.io.StdIn.readLine()
  }
}
