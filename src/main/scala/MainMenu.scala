import java.util
import java.util.ArrayList
class MainMenu {
  def show() = {
    try {
      val bookList: ArrayList[Book] = new util.ArrayList[Book]()
      val book1: Book = new Book()
      book1.name = "The Hobbit"
      book1.bookID = 1;
      bookList.add(book1)
      val book2: Book = new Book()
      book2.name = "Lord of the Rings"
      book2.bookID = 2;
      bookList.add(book2)
      var exit: Boolean = false
      while (!exit) {
        try{
          println("Book Store!")
          println()
          println("01. List down all the books.")
          println("02. View detailed information about a book.")
          println("03. Add a new book.")
          println("04. Purchase a book.")
          println("05. Exit")
          println()
          println("Enter your choice and hit Enter")
          val input: Int = scala.io.StdIn.readInt()
          input match {
            case 1 => val listMenu: ListMenu = new ListMenu()
              listMenu.show(bookList)
            case 2 => val detailsMenu: BookDetailsMenu = new BookDetailsMenu()
              detailsMenu.show(bookList)
            case 3 => val addBookMenu: AddBookMenu = new AddBookMenu()
              addBookMenu.show(bookList)
            case 4 => val purchaseMenu: PurchaseMenu = new PurchaseMenu()
              purchaseMenu.show(bookList)
            case 5 => exit = true
          }
        }
        catch {
          case ex: Exception => {
            println()
            println("Exception Occurred!")
            println(ex.toString)
            println()
          }
        }

      }
    }
    catch {
      case ex: Exception => {
        println()
        println("Exception Occurred!")
        println(ex.toString)
        println("Bye!")
      }
    }
  }
}
