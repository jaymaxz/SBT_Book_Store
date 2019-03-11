class Book() {
  private[this] var _bookID: Int = 0

  def bookID: Int = _bookID

  def bookID_=(value: Int): Unit = {
    _bookID = value
  }

  private[this] var _name: String = ""

  def name: String = _name

  def name_=(value: String): Unit = {
    _name = value
  }

}
