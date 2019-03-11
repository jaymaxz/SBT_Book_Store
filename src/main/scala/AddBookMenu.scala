import java.util.ArrayList

class AddBookMenu {
  def show(bookList: ArrayList[Book]) = {
    println()
    println("Add a new book")
    println()
    println("Press Enter Book name and press enter")
    val bookName: String = scala.io.StdIn.readLine()
    val book2: Book = new Book()
    book2.name = bookName
    book2.bookID = bookList.size()+1;
    bookList.add(book2)
    println()
    println("Book added successfully")
  }
}




