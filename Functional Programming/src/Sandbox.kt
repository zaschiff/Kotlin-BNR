fun main(args: Array<String>) {
    val gradesByStudent =  mapOf("Josh" to  4.0, "Alex" to 2.0, "Jane" to 3.0)
    val reversedGrades = gradesByStudent.flipValues()
    println(reversedGrades)

    val valuesToAdd = listOf(1,18,73,3,44,6,1,33,2,22,5,7)
    val filteredList = valuesToAdd.filter {it >= 5}.chunked(2).map {it[0] * it[1]}.fold(0) {
        a, n -> a + n
    }
    println(filteredList)
}
//CHALLENGE: Reverse Values
fun <K,V> Map<K,V>.flipValues(): Map<V, K> {
    return this.map{ it.value to it.key }.toMap()
}