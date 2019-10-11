/*
*
* Application:  Sim Village
* Author: Zach Schiff
* Date: 10/7/19
* Version: 1.0
* Description: simple app to show and understand anonymous functions
* in Kotlin
*
 */

fun main(args: Array<String>) {
    /*
    This function below is the example of using a variable as an anonymous function:

    val greetingFunction = { playerName: String, numBuildings: Int ->
        val currentYear = 2018
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }

    the variable uses inference to understand the type of the variable being
    the function type (NOT FUNCTION!. When inference is used the parameter's
    type must be stated. This function could also have been written as:

    val greetingFunction: (String, Int) -> String {BODY OF CODE}

    which means the greetingFunction is a variable that is a function that
    takes a string and int as parameters and returns a string

    if only one parameter than the keyword it can be used rather than naming
    the parameter but only use when easy to read otherwise name the variable

    a very basic anonymous function is below:

        {
            val currentYear = 2018
            "Welcome to SimVillage, Mayor! (copyright $currentYear)"
        }

    the return keyword is not allowed. the last line of the function is
    returned. In the example above the string "Welcome to SimVillage..." is
    returned.

    when a function accepts a function type as it's last parameter, one can omit
    the parentheses around the lambda expression (anonymous function body)
    as seen below:

    the code below was using an anonymous function bas an argument
    the next example wants to show a function as a return type

    runSimulation("Guyal", ::printConstructionCost) { playerName: String, numBuildings: Int ->
        val currentYear = 2018
        println("Adding $numBuildings houses")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }

     */

    runSimulation()
}

/* This function below takes a String and an anonymous function as an argument.
 the anonymous function must take a string and in as parameters
and return a string.

inline fun runSimulation(playerName: String,
                         costPrinter: (Int) -> Unit,
                         greetingFunction: (String, Int) -> String) {
    val numBuildings = (1..3).shuffled().last() // Randomly selects 1, 2, or 3
    costPrinter(numBuildings)
    println(greetingFunction(playerName, numBuildings))
}

anonymous functions have the ability to reach out of scope variables
the below code doe work

fun runSimulation() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("Guyal")
    println(greetingFunction("Guyal")
    }

this would return something like this:
    building 6 hospitals
    Welcome to SimVillage, Guyal! (copyright 2018)
    building 7 hospitals
    Welcome to SimVillage, Guyal! (copyright 2018)
*/

// this function does not do much but set up a function to be returned
// by another function
fun runSimulation() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("Guyal"))
}

// this function will be an example of returning function another function
fun configureGreetingFunction(): (String) -> String {
    val structureType = "hospitals"
    var numBuildings = 5
    return { playerName: String ->
        val currentYear = 2018
        numBuildings += 1
        println("Adding $numBuildings $structureType")
        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
    }
}



/* this function will be used to show an example for function reference
fun printConstructionCost(numBuildings: Int) {
    val cost = 500
    println("construction cost: ${cost * numBuildings}")
}
 */

