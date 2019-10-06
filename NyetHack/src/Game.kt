fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false

    // Aura control
    val auraVisible =  (isBlessed && healthPoints > 50 || isImmortal)
    val auraColor = if (auraVisible) "GREEN" else "NONE"
    //println(auraColor)

    /* a simple if control to check healthPoints being stored in a variable
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
     */

    // refactored the above if/else control to a when statement and ranges.
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

    // Printing the player status with string concatenation
    // println(name + " " + healthStatus)

    // Player status with string templates
    println("(Aura: $auraColor} " +
            "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}