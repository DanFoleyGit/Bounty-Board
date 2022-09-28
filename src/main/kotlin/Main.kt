const val HERO_NAME = "Madrigal"
var playerLevel = 0


fun main() {
    println("$HERO_NAME starts her adventure") // quick string  concatenation (interpolation)
    println("what level is $HERO_NAME?")
    val playerLevelInput = readLine()!!
    playerLevel = if (playerLevelInput.matches("""\d+""".toRegex())) {
        playerLevelInput.toInt()
    }  else {
        1
    }
    println("$HERO_NAME's level is $playerLevel.")

    readBountyBoard()

    println("Time passes...")
    println("$HERO_NAME from the quest.")
    playerLevel += 1
    println(playerLevel)
}

private fun readBountyBoard(){
//    println("$HERO_NAME approaches the bounty board. It reads:")
//    println("\t\"${obtainQuest(playerLevel)}\"") // string formatting the returned string from obtainQuest()
    println(
        """
            |$HERO_NAME approaches the bounty board. It Reads:
            |   "${obtainQuest(playerLevel).replace("Nogartes","xxxxxxxx")}"
        """.trimMargin()
    )
}

private fun obtainQuest(
    playerLevel: Int,
    playerClass: String = "paladin",
    hasAngerBarbarians: Boolean = false,
    hasBefriendedBarbarians: Boolean = true


    // single expression function. ie returns the result of the when statement using the = instead of return keyword
): String = when (playerLevel) {
    1 -> "meet mr bubbles in the land of soft things"
    in 2..5 -> { // range 2 to 5 inclusive
        // Check if diplomacy is an option
        val canTalkToBarbarians = !hasAngerBarbarians &&
                (hasBefriendedBarbarians || playerClass == "barbarian")

        if (canTalkToBarbarians) {
            "Convince the barbarians to call off invasion"
        } else {
            "Save the town from barbarians."
        }
    }
    6 -> "Locate the enchanted sword"
    7 -> "Recover the long lost artifact."
    8 -> "Defeat Nogartes"
    else -> "There are no quests right now."
}
