import java.util
import java.util.ArrayList

import BookStore.{AddBookMenu, Book, BookDetailsMenu, DeleteMenu, ListMenu}
import org.scalatest.FunSuite

class Test extends FunSuite{
  val bookList: ArrayList[Book] = new util.ArrayList[Book]
  val book1 = new Book(1, "The Hobbit")
  bookList.add(book1)
  val book2 = new Book(2, "Lord of the Rings")
  bookList.add(book2)

  //restart the server before each test run

  test("ListMenuTest") {
    val listMenu = new ListMenu()
    listMenu.show()
    assert(listMenu.BookList.get(0).name=== bookList.get(0).name && listMenu.BookList.get(1).name=== bookList.get(1).name)
  }

  test("BookDetailsMenuTest") {
    val bookDetailsMenu = new BookDetailsMenu()
    bookDetailsMenu.show() // add 1 to book id
    assert(bookDetailsMenu.BookName === "The Hobbit")
  }

  test("AddBookMenuTest") {
    val addBookMenu = new AddBookMenu()
    addBookMenu.show() // add book name as "abc"
    assert(addBookMenu.BookName === "abc")
  }

  test("DeleteMenuTest") {
    val deleteMenu = new DeleteMenu()
    deleteMenu.show() // add 1 to book id
    assert(deleteMenu.BookName === "The Hobbit")
  }
}
