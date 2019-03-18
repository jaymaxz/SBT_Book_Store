import java.util

import com.google.gson.{Gson, JsonArray, JsonParser}
import scalaj.http.Http

class ListMenu {
  def show() = {
    println()
    println("Book List")
    println()

    val result = Http("http://localhost:8000/getAllBooks").asString
    //val connection = (new URL("http://localhost:8000/test")).openConnection.asInstanceOf[HttpURLConnection]
    //connection.setConnectTimeout(5000)
    //connection.setReadTimeout(5000)
    //connection.setRequestMethod("GET")
    //val iS: InputStream = connection.getInputStream
    //val reader: Reader = new InputStreamReader(iS, "UTF-8")
    val jsonPsr: JsonParser =new JsonParser()
    //val jsonObj: JsonObject = jsonPsr.parse(result.body).asInstanceOf[JsonObject]
    val jsonAry: JsonArray = jsonPsr.parse(result.body).asInstanceOf[JsonArray]
    val bookList: util.ArrayList[Book] = new util.ArrayList[Book]()
    val gson: Gson = new Gson()
    jsonAry.forEach(jsnObj => bookList.add(gson.fromJson(jsnObj, classOf[Book])))
    //val bookList: util.ArrayList[Book] = gson.fromJson(jsonAry, classOf[util.ArrayList[Book]])
    //val bookList: util.ArrayList[Book] = gson.fromJson(reader, classOf[util.ArrayList[Book]])
    bookList.forEach((book: Book)=>println(book.bookID+"."+book.name))
    println()


    println("Press Enter to go back")
    scala.io.StdIn.readLine()
  }
}
