fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false
    var auraColor = "NONE"

    // Aura control
    val auraVisible =  (isBlessed && healthPoints > 50 || isImmortal)
    //val auraColor = if (auraVisible) "GREEN" else "NONE"
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

    // CHALLENGE: ENHANCING THE AURA
    if (auraVisible) {
        val karma = (Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20).toInt()
        auraColor = when (karma) {
            in 16..20 -> "GREEN"
            in 11..15 -> "PURPLE"
            in 6..10 -> "ORANGE"
            else -> "RED"
        }
    }

    // refactored the above if/else control to a when statement and ranges.
    val healthStatus = when (healthPoints) {
        100       -> "is in excellent condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 ->
                if (isBlessed) {
                    "has some minor wounds but is healing quite quickly!"
                } else {
                     "has some minor wounds."
                }
        in 15..74 -> "looks pretty hurt."
        else      -> "is in awful condition!"
    }

    // Printing the player status with string concatenation
    // println(name + " " + healthStatus)

    /* Player status with string templates
    println("(Aura: $auraColor} " +
            "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
     */

    // CHALLENGE: CONFIGURABLE STATUS FORMAT
    val playerStatus = "(HP)(A) -> H"
        .replace("HP", "HP: $healthPoints")
        .replace("A", "Aura: $auraColor")
        .replace("-> H", "-> $name $healthStatus")
    println(playerStatus)
}