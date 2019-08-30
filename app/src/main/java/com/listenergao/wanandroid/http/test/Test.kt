package com.listenergao.wanandroid.http.test


fun main() {

    val maxCount = 100_000
    //Array 方式
//    var timeArrayStart = LocalTime.now()
//    var array = Array(maxCount) { i -> (i + 1) }
//    var totalArray = 0
//    array.forEach {
//        totalArray += it
//    }
//    val avgArray = BigDecimal(totalArray).divide(BigDecimal(array.size))
//    val durationArray = Duration.between(timeArrayStart, LocalTime.now())
//    Log.e("gys", "Array 平均值=$avgArray 用时=$durationArray")

//    initArray(maxCount)
//    initIntArray(maxCount)
//    initList(maxCount)

    val person = Person("ListenerGao", 26)
    val student = Student(person)
    student.show()

    val array = listOf(21, 40, 11, 33, 78)
    //过滤
    val list = array.filter { i ->
        i % 3 == 0
    }
    println(list.toString())

}

fun initArray(size: Int) {
    val startTime = System.currentTimeMillis()
    val array = Array(size) { it + 1 }
    var totalArray = 0
    array.forEach {
        totalArray += it
    }
    val avg = totalArray / array.size
    val time = System.currentTimeMillis() - startTime
    println("initArray avg =  $avg  time = $time")

}

fun initIntArray(size: Int) {

    val startTime = System.currentTimeMillis()
    val array = IntArray(size) { it + 1 }
    var totalArray = 0
    array.forEach {
        totalArray += it
    }
    val avg = totalArray / array.size
    val time = System.currentTimeMillis() - startTime
    println("initIntArray avg =  $avg  time = $time")
}

fun initList(size: Int) {
    val startTime = System.currentTimeMillis()
    val array = List(size) { it + 1 }
    var totalArray = 0
    array.forEach {
        totalArray += it
    }
    val avg = totalArray / array.size
    val time = System.currentTimeMillis() - startTime
    println("initList avg =  $avg  time = $time")
}

class Student(val name: String, private val age: Int) {

    constructor(name: String) : this(name, 0)

    constructor(person: Person) : this(person.name, person.age)

    fun show() {
        print("name = $name, age = $age")
    }


}

class Person(val name: String, val age: Int)

