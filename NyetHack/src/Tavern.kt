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


const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10
// CHALLENGE VARIABLES
var pubCask = 5.0
var dragonCoin = 5.0

fun main(args: Array<String>) {
    placeOrder("Shandy,Dragon's Breath,5.91")
}

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price"
    println(message)

    performPurchase(price.toDouble(), true)
//    performPurchase(price.toDouble())
//    performPurchase(price.toDouble())

    val phrase = if (name == "Dragon's Breath") {
        "Madrigal exclaims ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "Madrigal says: Thanks for the $name."
    }

    println(phrase)

    println()
    inventoryCheck(12)
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

// CHALLENGE: DRAGONCOIN
fun performPurchase (price: Double, payWithDragonCoin: Boolean) {
    displayBalance()
    if (!payWithDragonCoin) {
        val totalPurse = playerGold + (playerSilver / 100.0)
        println("Total purse: $totalPurse")
        // CHALLENGE: HANDLING A NEGATIVE BALANCE, to simulate the loss of fund uncomment
        // the additional performPurchase() calls in the placeOrder function.
        if (totalPurse < price) {
            val missingAmount = price - totalPurse
            println("Sorry Madrigal, but you are a bit short of funds.")
            println("You seem to be short ${"%.2f".format(missingAmount)}")
            displayBalance()
        } else {
            println("Purchasing item for $price")

            val remainingBalance = totalPurse - price
            println("Remaining balance: ${"%.2f".format(remainingBalance)}")

            val remainingGold = remainingBalance.toInt()
            val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
            playerGold = remainingGold
            playerSilver = remainingSilver
            displayBalance()
        }
        //CHALLENGE: DRAGONCOIN
    } else {
        val dragonCoinPrice = price / 1.43
        if (dragonCoinPrice > dragonCoin) {
            val missingAmount = dragonCoinPrice - dragonCoin
            println("Sorry Madrigal, but you are a bit short of funds.")
            println("You seem to be short ${"%.2f".format(missingAmount)}")
            displayBalance()
        } else {
            println("Purchasing item for ${"%.2f".format(dragonCoinPrice)}")
            val remainingDragonBalance = dragonCoin - dragonCoinPrice
            println("Remaining blance: ${"%.4f".format(remainingDragonBalance)}")
            dragonCoin = remainingDragonBalance
            displayBalance()
        }
    }
}

private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold, Silver: $playerSilver")
}

//CHALLENGE: REMAINING PINTS, the function is called at the end of the placed order function
private fun inventoryCheck(pintsSold: Int) {
    val gallonsUsed = pintsSold * .125
    val gallonsLeft = pubCask - gallonsUsed
    println("After $pintsSold pints sold, there are $gallonsLeft gallons " +
            "left in the pub cask.")
    pubCask = gallonsLeft
}