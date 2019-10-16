/*
*
* Application: NyetHack/Tavern.kt
* Author: Zach Schiff
* Date: 10/11/2019
* Version: 1.0
* Description: additional file for NyetHack, mean to show the examples concerning
* the NULL value.
*
 */

import kotlin.math.roundToInt
import java.io.File


const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")

fun main(args: Array<String>) {
    // CHALLENGE: FORMATTED TAVERN MENU
    val greeting = "*** Welcome to $TAVERN_NAME ***"
    val menuWidth = greeting.length
    println(greeting + "\n")
    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }

    // CHALLENEGE 9: FORMATTED TAVERN MENU
    for (item in menuList) {
        val (_, name, price) = item.split(",")
        val capName = capitalizeWord(name)
        val pad = menuWidth - price.length
        val menuItem = capName.capitalize().padEnd(pad, '.') + price
        println(menuItem)
    }

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.shuffled().first(),
            menuList.shuffled().first())
        orderCount++
    }
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price"
    println(message)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "$patronName says: Thanks for the $name."
    }

    println(phrase)
}

private fun toDragonSpeak(phrase : String) =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

fun performPurchase (price: Double) {
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")

    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance %1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}

private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold, Silver: $playerSilver")
}

private fun capitalizeWord(text: String): String{
    var capText = ""
    val words = text.split(" ").toMutableList()
    for (word in words) {
        if (word.length > 2) {
            capText += word.capitalize() + " "
        } else {
            capText += word + " "
        }
    }
    capText = capText.trim()
    return capText
}