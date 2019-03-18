import com.google.gson.Gson
import scalaj.http.{Http, HttpOptions}

class BookDetailsMenu {
  def show() = {
    println()
    println("Book Details")
    println()
    println("Press Enter Book ID number and press enter")
    val bookID: Int = scala.io.StdIn.readInt()
    val gson: Gson = new Gson()
    var index = gson.toJson(bookID-1)
    val result = Http("http://localhost:8000/getBookDetails").postData(index)
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .option(HttpOptions.readTimeout(10000)).asString
    val book: Book = gson.fromJson(result.body, classOf[Book])
    println()
    println("Book ID: "+book.bookID+"\nBook Name: " +book.name)
    println()
    println("Press Enter to go back")
    scala.io.StdIn.readLine()
  }
}
