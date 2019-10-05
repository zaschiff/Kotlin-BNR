/*
**  Applicaiton: Sandbox
**  Author: Zach Schiff
**  Date: 10/05/2019
**  Version: 1.1
**  Description: This is a simple sandbox app to learn kotlin code from
**  the training book Kotlin Programming: The Big Nerd Ranch Guide
 */

// All challenge solutions will have accompanying
// print statements at end of code


const val MAX_EXPERIENCE: Int = 5000

fun main(args: Array<String>) {
    val playerName = "Estragon"
    var experiencePoints = 5
    //Challenge: hasSteed
    var hasSteed = false

    //Challenge: the Unicorn's Horn
    val pubName = "Unicorn's Horn"
    val currentPublican = "Smitty"
    var gold = 50
    val menu = listOf("Mead", "Wine", "LaCroix")

    experiencePoints += 5
    println(experiencePoints)
    println(playerName)
    println(hasSteed)
    println(pubName)
    println(currentPublican)
    println(gold)
    println(menu)

    // Challenge: Magic Mirror
    println(playerName.reversed())
}