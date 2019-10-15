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

fun main(args: Array<String>) {
    placeOrder("Shandy,Dragon's Breath,5.91")
//    placeOrder("Elixir,Shirley's Temple,4.12")
}

// function to place an order from menu items
private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    /*
     the below info is separating the menu item by the comma(,)
     and grabs the info via array index notation.
    val data = menuData.split(',')
    val type = data[0]
    val name = data[1]
    val price = data[2]
    */

    // using deconstruction, also known assigning multiple variables
    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price"
    println(message)

    performPurchase(price.toDouble())

    /*
        need a check for dragon's breath
        val phrase = "Ah, delicious $name!"
        println("Madrigal exclaims: ${toDragonSpeak(phrase)}")
     */

    val phrase = if (name == "Dragon's Breath") {
        "Madrigal exclaims ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "Madrigal says: Thanks for the $name."
    }

    println(phrase)
}

//function to show string's replace funtion and make a new dragon
//speak translator
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


    /*
        NOTES ON NULL, NULLABILITY, SAFE CALLS, AND DEALING WITH NULL VALUES


     ASSIGNING A NULL VALUE

        the below code was an attempted to assign a null value to a
        non-nullable type.

            var signatureDrink = "Buttered Ale"
            signatureDrink = null




     SAFECALL OPERATOR (?)

       the below code does not run as we have not dealt with the value
       of readline being null. the new code uses the safe call operator
       (?.)

            var beverage = readLine()?.capitalize()



     LET FUNCTION

     the below code use the function of let to run additional code on
     a safe call operator as the safe call only allows one additional
     function
            var beverage = readLine()?. let {
                if (it.isNotBlank()) {
                    it.capitalize()
                } else {
                    "Buttered Ale"
                }
            }




     DOUBLE BANG OPERATOR (!!)

        the below code uses the double bang operator to assert the desire for
        the null pointer exception. This should be used when the viable is known
        never be null.

             var beverage = readLine()!!.capitalize()


     USING != TO CHECK FOR A NULL VALUE
    var beverage = readLine()
    beverage = null

    if (beverage != null) {
        beverage = beverage.capitalize()
    } else {
        println("I can't do that without crashing - beverage was null!")

    }

     Below is the null coalescing operator (elvis operator) which says:
     "If the thing on the left is null then use the thing on the right."
    val beverageServed: String = beverage ?: "Buttered Ale"
    println(beverageServed)

     When dealing with null there should be an order of choice with handling null
    values.
        Safe call operator -> this can be piped and condensed due
            to smart casting

        If-else or != null check -> should be use when more complex and
            intricate logic is need for when a value is null or not

        Let function -> quick check that allows more intricate logic than a simple
            pipe but not as intricate as what can be done with the if else

        Double Bang Operator
     */