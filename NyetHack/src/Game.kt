fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false

    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)

    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    printPlayerStatus(auraColor, isBlessed, name, healthStatus)

    val inebriation = castFireball()
    println(inebriation)
}

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

// CHALLENGE: SINGLE-EXPRESSION FUNCTIONS
private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean) =
    if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"

// CHALLENGE: FIREBALL INEBRIATION LEVEL
// CHALLENGE: INEBRIATION STATUS
private fun castFireball(numFireballs: Int = 2): String {
    println("A glass of fireball springs into existence. (x$numFireballs)")
    return when ((0..50).random()) {
        in 1..10  -> "tipsy"
        in 11..20 -> "sloshed"
        in 21..30 -> "soused"
        in 31..40 -> "stewed"
        else      -> "..t0aSt3d"
    }
}

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

