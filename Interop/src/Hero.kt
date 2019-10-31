@file:JvmName("Hero")

import java.io.IOError
import java.io.IOException
import java.lang.Exception

fun main(args: Array<String>) {
    val adversary = Jhava()
    println(adversary.utterGreeting())

    val friendshipLevel = adversary.determineFriendshipLevel()
    println(friendshipLevel?.toLowerCase() ?: "It's complicated.")

    val adversaryHitPoints: Int = adversary.hitPoints
    println(adversaryHitPoints.dec())
    println(adversaryHitPoints.javaClass)

    adversary.greeting = "Hello, Hero."
    println(adversary.utterGreeting())

    adversary.offerFood()

    try {
        adversary.extendHandInFriendship()
    } catch (e: Exception) {
        println("Begone, Foul beast!")
    }
}

val translator = {utterance: String ->
    println(utterance.toLowerCase().capitalize())
}

fun makeProclamation() = "Greetings, beast!"

@JvmOverloads
fun handOverFood(leftHand: String = "berries", rightHand: String = "beef") {
    println("Mmm...you hand over some delicious $leftHand and $rightHand.")
}

@Throws(IOException::class)
fun acceptApology() {
    throw IOException()
}

class Spellbook {
    @JvmField
    val spells = listOf("Magic Ms. L", "Lay on Hans")

    companion object {
        @JvmField
        val MAX_SPELL_COUNT = 10

        @JvmStatic
        fun getSpellbookGreeting() = println("I am the Great Grimoire!")
    }
}