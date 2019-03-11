import java.util.ArrayList

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import scalaj.http.{Http, HttpOptions}

class PurchaseMenu {
  def show(bookList: ArrayList[Book]) = {
    println()
    println("Purchase a Book")
    println()
    println("Press Enter Book ID number and press enter")
    val bookID: Int = scala.io.StdIn.readInt()
    val book: Book = bookList.get(bookID-1)
    var jsonObject: JSONObject = new JSONObject()
    val jsonString: String = "{\"BookID\": " + book.bookID + ",\"Book Name\": \"" + book.name + "\"}"
    val parser: JSONParser = new JSONParser();
    jsonObject  = parser.parse(jsonString).asInstanceOf[JSONObject]
    val result = Http("http://localhost:8000/test").postData(jsonObject.toJSONString)
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
