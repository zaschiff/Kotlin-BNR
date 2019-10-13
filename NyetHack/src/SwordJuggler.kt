/*
*
* File: NyetHack/SwordJuggler.kt
* Author: Zach Schiff
* Date: 10/12/2019
* Version:1.0
* Description: simple file to illustrate exception handling in kotlin
*
 */

import kotlin.IllegalStateException

fun main(args: Array<String>) {
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient) {
        swordsJuggling = 2
    }

    //simple try-catch system similar to an if-else
    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    } catch(e: Exception) {
        println(e)
    }

    println("You juggle $swordsJuggling swords!")
}

// simple function to throw an exception
fun proficiencyCheck(swordsJuggling: Int?) {
    checkNotNull(swordsJuggling, { "Player cannot juggle swords" })
}

// custom exception built as a class which is covered in chapter 12, which
// is later after this chapter (6).
class UnskilledSwordJugglingException() :
        IllegalStateException("Player cannot juggle swords")