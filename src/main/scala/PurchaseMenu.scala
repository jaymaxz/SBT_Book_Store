import java.util
import java.util.ArrayList

import com.google.gson.Gson
import scalaj.http.{Http, HttpOptions}

class PurchaseMenu {
  def show(bookList: ArrayList[Book]) = {
    println()
    println("Purchase a Book")
    println()
    println("Press Enter Book ID number and press enter")
    //val bookID: Int = scala.io.StdIn.readInt()
    //val book: Book = bookList.get(bookID-1)

    val bookList: ArrayList[Book] = new util.ArrayList[Book]()
    val book1: Book = new Book()
    book1.name = "The Hobbit"
    book1.bookID = 1;
    bookList.add(book1)
    val book2: Book = new Book()
    book2.name = "Lord of the Rings"
    book2.bookID = 2;
    bookList.add(book2)
    val gson: Gson = new Gson()
    val sda = gson.toJson(bookList)
    val result = Http("http://localhost:8000/test").postData(sda)
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .option(HttpOptions.readTimeout(10000)).asString
    println()
    println(result.body)
    println()
    println("Press Enter to go back")
    scala.io.StdIn.readLine()
  }
}
