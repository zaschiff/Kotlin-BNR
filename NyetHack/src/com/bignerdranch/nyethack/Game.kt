package com.bignerdranch.nyethack

import kotlin.system.exitProcess

/*
*
* Application: NyetHack/Game.kt
* Author: Zach Schiff
* Date: 10/06/2019
* Version: 1.3
* Description: NyetHack is a simple text based game to illustrate
* basic kotlin programming syntax and other kotlin topics.
*
 */

fun main(args: Array<String>) {
    Game.play()
}

object Game {
    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    init {
        println("Welcome, adventurer.")
        player.castFireball()
    }

    fun play() {
        while (true) {
            //play Nyethack
            println(currentRoom.description())
            println(currentRoom.load())

            // Refactored print statements into a function
            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
    }


    private fun printPlayerStatus(player: Player) {
        println(
            "(Aura: ${player.auraColor()} " +
                    "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
        )
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private class GameInput (arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() =
            when (command.toLowerCase()) {
                "fight" -> fight()
                "move" -> move(argument)
                else -> commandNotFound()
            }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction is out of bounds.")
            }

            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n{$newRoom.load()}"
        } catch(e: Exception) {
            "Invalid direction: $directionInput."
        }

    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0) {
            slay(it)
            Thread.sleep(1000)
        }
        "Combat complete."
    } ?: "There's nothing here to fight."

    private fun slay(monster: Monster) {
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")

        if (player.healthPoints <= 0) {
            println(">>>> You have been defeated! Thanks for Playing. <<<<")
            exitProcess(0)
        }

        if (monster.healthPoints <= 0) {
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }
    }
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

     com.bignerdranch.nyethack.Player status with string templates
     println("(Aura: $auraColor} " +
            "(Blessed: ${if (isBlessed) "YES" else "NO"})")
     println("$name $healthStatus")

     the below code was encapsulated in the player class
        var healthPoints = 89
        val isBlessed = true
        val isImmortal = false


        New function to design the healthStatus string. The function visibility
        is private as it is only called once, the fun keyword to denote in kotlin
        that it is a function, the name, the parameters and their types in parentheses,
        and finally the return type if the function returns something.


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



            New Function designed for Aura Color following the same principals as
            formatHealthStatus



            private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean): String {
                val auraVisible = (isBlessed && healthPoints > 50 || isImmortal)
                val auraColor = if (auraVisible) "GREEN" else "NONE"
                return auraColor
            }
     */