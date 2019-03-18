import java.util
import com.google.gson.{Gson, JsonArray, JsonParser}
import scalaj.http.Http

class ListMenu {
  def show() = {
    println()
    println("Book List")
    println()
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
