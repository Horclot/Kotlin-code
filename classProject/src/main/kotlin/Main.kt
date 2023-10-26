fun main() {
    Car("Honda", "Red", 2, 240).also {
        it.printCar()
        println(it)
    }
}
class Car(
    private var name: String,
    private var color:String,
    private var long: Int,
    private var speed: Int
) {
    fun printCar() {
        println("Машина $name, цвет - $color, длинна - $long, макс скорость $speed км/ч")
    }
}