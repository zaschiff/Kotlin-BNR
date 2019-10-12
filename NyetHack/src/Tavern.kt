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


fun main(args: Array<String>) {
    /*
     ASSIGNING A NULL VALUE

        the below code was an attempted to assign a null value to a
        non-nullable type.

            var signatureDrink = "Buttered Ale"
            signatureDrink = null

    */

    /*
     SAFECALL OPERATOR (?)

       the below code does not run as we have not dealt with the value
       of readline being null. the new code uses the safe call operator
       (?.)

            var beverage = readLine()?.capitalize()
    */

    /*
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
     */


    /*
     DOUBLE BANG OPERATOR (!!)

        the below code uses the double bang operator to assert the desire for
        the null pointer exception. This should be used when the viable is known
        never be null.

             var beverage = readLine()!!.capitalize()
     */

    // USING != TO CHECK FOR A NULL VALUE
    var beverage = readLine()
    //beverage = null

    if (beverage != null) {
        beverage = beverage.capitalize()
    } else {
        println("I can't do that without crashing - beverage was null!")

    }

    // Below is the null coalescing operator (elvis operator) which says:
    // "If the thing on the left is null then use the thing on the right."
    val beverageServed: String = beverage ?: "Buttered Ale"
    println(beverageServed)

    /* When dealing with null there should be an order of choice with handling null
    values.
        Safe call operator -> this can be piped and condensed due
            to smart casting

        If-else or != null check -> should be use when more complex and
            intricate logic is need for when a value is null or not

        Let function -> quick check that allows more intricate logic than a simple
            pipe but not as intricate as what can be done with the if else

        Double Bang Operator
     */
}