fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false

    //refactored Aura calls into a function
    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)

    // Refactored when structure into a function to be called
    // below is the function call
    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    // Refactored print statements into a function
    printPlayerStatus(auraColor, isBlessed, name, healthStatus)

    castFireball()
}

/*
 Refactored print statements into  single function
*/

private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healthStatus: String
) {
    println(
        "(Aura: $auraColor} " +
                "(Blessed: ${if (isBlessed) "YES" else "NO"})"
    )
    println("$name $healthStatus")
}

/*
New Function designed for Aura Color following the same principals as
formatHealthStatus
*/


private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean): String {
    val auraVisible = (isBlessed && healthPoints > 50 || isImmortal)
    val auraColor = if (auraVisible) "GREEN" else "NONE"
    return auraColor
}

// Creating castFireball function as the book does
private fun castFireball(numFireballs: Int = 2) =
    println("A glass of fireball springs into existence. (x$numFireballs)")

/*
New function to design the healthStatus string. The function visibility
is private as it is only called once, the fun keyword to denote in kotlin
that it is a function, the name, the parameters and their types in parentheses,
and finally the return type if the function returns something.
 */

private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) =
    when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly!"
        } else {
            "has some minor wounds."
        }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }


/*Aura control
    val auraVisible =  (isBlessed && healthPoints > 50 || isImmortal)
    val auraColor = if (auraVisible) "GREEN" else "NONE"

    println(auraColor)

    a simple if control to check healthPoints being stored in a variable
        kotlin evaluates if from top to bottom. ORDER MATTERS!
    val healthStatus =
        if (healthPoints == 100) {
            "is in excellent condition!"
        } else if (healthPoints in 90..99) {
            "has a few scratches."
        } else if (healthPoints in 75..89) {
            // nested if else statement
            if (isBlessed) {
                "has some minor wounds but is healing quickly!"
            } else {
                "has some minor wounds."
            }
        } else if (healthPoints in 15..74) {
            "looks pretty hurt."
        } else {
            "is in awful condition!"
        }

    refactored the above if/else control to a when statement and ranges.
    val healthStatus = when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly!"
        } else {
            "has some minor wounds."
        }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }


     Printing the player status with string concatenation
     println(name + " " + healthStatus)

     Player status with string templates
     println("(Aura: $auraColor} " +
            "(Blessed: ${if (isBlessed) "YES" else "NO"})")
     println("$name $healthStatus")
     */