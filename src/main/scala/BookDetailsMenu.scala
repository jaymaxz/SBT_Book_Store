import java.util.ArrayList

class BookDetailsMenu {
  def show(bookList: ArrayList[Book]) = {
    println()
    println("Book Details")
    println()
    println("Press Enter Book ID number and press enter")
    val bookID: Int = scala.io.StdIn.readInt()
    val book: Book = bookList.get(bookID-1)
    println()
    println("Book ID: "+book.bookID+"\nBook Name: " +book.name)
    println()
    println("Press Enter to go back")
    scala.io.StdIn.readLine()
  }
}
