const val HERO_NAME = "Madrigal"
var playerLevel = 0

fun main() {
    println("$HERO_NAME starts her adventure") // quick string  concatenation (interpolation)
    println("what level is $HERO_NAME?")
    playerLevel = readLine()?.toIntOrNull() ?: 0 //  if not int, becomes null, elvis then applies the value on the right
    println("$HERO_NAME's level is $playerLevel.")

    readBountyBoard()

    println("Time passes...")
    println("$HERO_NAME returns from her quest.")
    playerLevel += 1
    println(playerLevel)
    readBountyBoard()
}

private fun readBountyBoard(){
    val message: String = try {
        val quest: String? = obtainQuest(playerLevel)
        // safe call operator - if the value is null, it will skip over the function after ?.
        quest?.replace("Nogartes", "XXXXXXXX")
            ?.let { censoredQuest ->// safe call with let that creates censorQuest var
                """
                |$HERO_NAME approaches the bounty board. It Reads:
                |   "$censoredQuest"
                """.trimMargin()
            }
            ?: "$HERO_NAME approaches the bounty board, but it is blank." // elvis operator. if left of ?: is null, assign right of ?:
    } catch (e: Exception) {
        "$HERO_NAME cant read what's on the bounty board."
    }
    println(message)

}

private fun obtainQuest(
    playerLevel: Int,
    playerClass: String = "paladin",
    hasAngerBarbarians: Boolean = false,
    hasBefriendedBarbarians: Boolean = true
): String? {
    require  (playerLevel <= 0) { // require throws an illegal argument exception here
        "The players level must be at least 1."
    }
    return when (playerLevel) {
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
        8 -> "Defeat Nogartes, bringer of death and eater of worlds"
        else -> null
    }
}
