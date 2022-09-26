
class Task {

    interface Publication {
        var price: Int
        var wordCount: Int
        fun getType(): String
    }

    class Book(override var price: Int, override var wordCount: Int) : Publication{
        override fun getType(): String {
            return when(this.wordCount){
                in 0..1000->"Flash fiction"
                in 1001..7500->"Short story"
                else -> "Novel"
            }
        }
    }

    class Magazine(override var price: Int, override var wordCount: Int) : Publication{
        override fun getType(): String = "magazine"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other){
            return true
        }
        return false
    }

    companion object{
        private fun buy(p: Publication){
            println("The purchase is complete. The purchase amount was ${p.price}")
        }

        fun start(){
            val warAndPeace = Book(10000000, 100000000)
            val whiteTeeth = Book(109090, 213123)

            val magazine = Magazine(100, 20)

            println("Тип ${warAndPeace.getType()} Цена: ${warAndPeace.price} Количество слов: ${warAndPeace.wordCount}")
            println("Тип ${whiteTeeth.getType()} Цена: ${whiteTeeth.price} Количество слов: ${whiteTeeth.wordCount}")
            println("Тип ${magazine.getType()} Цена: ${magazine.price} Количество слов: ${magazine.wordCount}")


            println(warAndPeace == whiteTeeth) /* Сравнение через переопределённый equals */
            println(warAndPeace === whiteTeeth) /* Сравнение по ссылке */

            println(warAndPeace == warAndPeace) /* Сравнение через переопределённый equals */
            println(warAndPeace === warAndPeace) /* Сравнение по ссылке */

            var bookToBuy1: Book? = Book(1, 2122132)
            var bookToBuy2: Book? = null


            if (bookToBuy1 != null) {
                buy(bookToBuy1)
            }
            if (bookToBuy2 != null) {
                buy(bookToBuy2)
            }

            val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
            println(sum(1, 30))
        }
    }
}