fun main() {
    val list1 = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 9, 10)
    val list2 = listOf<Int>(2,4,6,8,10,12,15,7,-3)
    println(list1)
    println(list2)
    println("//////////////////////////////////////////////")
    var list3 = arrayListOf<Int>(1,2,3,4,5)
    println(list3)

    list3.add(2,10)
    println(list3)

    list3.add(104)
    println(list3)

    list3.addAll(list2)
    println(list3)

    list3.remove(1)
    println(list3)

    list3.removeAt(5)
    println(list3)

    list3.clear()
    println(list3)
    println("///////////////////////////////////////////////")

    val number = setOf(1, 2, 3, 4, 5)
    val people = setOf("Tom", "Jim", "Dima")

    for (person in number) println(person)
    println(number)



}