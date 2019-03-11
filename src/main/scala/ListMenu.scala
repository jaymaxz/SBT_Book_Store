import java.util.ArrayList

class ListMenu {
  def show(bookList: ArrayList[Book]) = {
    println()
    println("Book List")
    println()
    bookList.forEach((book: Book)=>println(book.bookID+"."+book.name))
    println()
    println("Press Enter to go back")
    scala.io.StdIn.readLine()
  }
}
