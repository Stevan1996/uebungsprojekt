import kotlin.random.Random

class DiceSimulator(private val args : Array<String>) : Program {
    private var numOfRolls =  3
        set(value) {
            if(value > 0) {
                field = value
            }
        }

    fun checkPasch(rolls: List<Int>) {
        println("Dice rolls: ${rolls.toString()}")
        if(rolls.distinct().count() == 1){
            println("are a pasch")
        }

        if(numOfRolls == 5 && rolls.distinct().count() == 2){
            println("are a full house")
        }
    }

    override fun execute() {
        val diceRolls = IntArray(numOfRolls){ Random(System.nanoTime()).nextInt(1, 7)}.asList()
        checkPasch(diceRolls)
    }
}