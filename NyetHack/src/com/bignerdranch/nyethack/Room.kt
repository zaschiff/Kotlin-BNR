package com.bignerdranch.nyethack

open class Room (val name: String) {
    protected open val dangerLevel = 5

    fun description() = "Room: $name\n" +
            "Danger Level: $dangerLevel"

    open fun load() = "Nothing much to see here..."
}

open class TownSquare : Room("Town Square") {
    override val dangerLevel = super.dangerLevel - 3
    private var bellSound = "GWONG"

    final override fun load() =
        "the villagers rally and cheer as you enter!\n${ringBell()}"

    fun ringBell() =
        "The bell tower announces your arrival. $bellSound"
}