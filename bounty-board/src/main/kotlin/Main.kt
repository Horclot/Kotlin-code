import java.lang.Exception
import java.lang.IllegalArgumentException

const val HERO_NAME = "Madrigal"
var playerLevel = 0
fun main() {

    println("$HERO_NAME announces her presence to the world.")
    println("What level is $HERO_NAME?")
    playerLevel = readln()?.toIntOrNull() ?: 0 //если null, то playerLevel = 0
    println("$HERO_NAME's level is $playerLevel")
    readBountyBoard()
    println("Time passes...")
    println("$HERO_NAME returns from her quest.")
    playerLevel++
    println(playerLevel)
    readBountyBoard()
}
private fun  readBountyBoard(){
    try {
        val quest: String? = obtainQuest(playerLevel)
        val censoredQuest: String? = quest?.replace("Nogartse","xxxxxxxx")
        if (censoredQuest != null){
            println("$HERO_NAME approaches the bounty board. It reads:")
            println(censoredQuest)
        }
    }catch (e: Exception){
        println("$HERO_NAME can't read what's one the bounty board.")

    }}
private fun obtainQuest(
    playerLevel: Int,
    playerClass: String = "paladin",
    hasAngeredBarbarians: Boolean = false,
    hasBefriendedBarbarians: Boolean = true,
): String? {
    if (playerLevel <= 0) {
        throw IllegalArgumentException("The player's level must be at least 1.")
    }
    return when (playerLevel) {
        1 -> "Meet Mr. Bubbles in the land of soft things."
        in 2..5 -> {
            val canTalkToBarbarians = !hasAngeredBarbarians && (hasBefriendedBarbarians || playerClass == "barbarians")
            if (canTalkToBarbarians) {
                ("Convince the barbarians to call off their invasion.")
            } else {
                "Save the town from the barbarian invasions."
            }
        }

        6 -> "Locate the enchanted sword."
        7 -> "Recover the long-lost artifact of creation."
        8 -> "Defeat Nogartse, bringer of death and eater of worlds."
        else -> null
    }
}