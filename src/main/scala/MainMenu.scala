class MainMenu {
  def show() = {
    try {
      var exit: Boolean = false
      while (!exit) {
        try{
          println("Book Store!")
          println()
          println("01. List down all the books.")
          println("02. View detailed information about a book.")
          println("03. Add a new book.")
          println("04. Delete a book.")
          println("05. Exit")
          println()
          println("Enter your choice and hit Enter")
          val input: Int = scala.io.StdIn.readInt()
          input match {
            case 1 => val listMenu: ListMenu = new ListMenu()
              listMenu.show()
            case 2 => val detailsMenu: BookDetailsMenu = new BookDetailsMenu()
              detailsMenu.show()
            case 3 => val addBookMenu: AddBookMenu = new AddBookMenu()
              addBookMenu.show()
            case 4 => val deleteMenu: DeleteMenu = new DeleteMenu()
              deleteMenu.show()
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
