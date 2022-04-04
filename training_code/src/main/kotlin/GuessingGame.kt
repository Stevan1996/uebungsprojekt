import kotlin.random.Random

class GuessingGame(private val args : Array<String>) : Program {
    init {
        if(args.size > 1) {
            throw IllegalArgumentException("You may only guess one at a time")
        }
    }

    override fun execute() {
        var guess : Int
        if(args.isNotEmpty()) {
            guess = args[0].toInt()
        } else {
            print("Enter your guess")
            guess = readLine()!!.toInt()
        }

        val randNum = Random(System.nanoTime()).nextInt(1001)

        do {
            when {
                guess == randNum -> {
                    println("Congrats! Your guessed right.")
                    continue
                }
                guess < randNum -> println("Guess was too low")
                guess > randNum -> println("Guess was too high")
            }

            print("Enter your guess")
            guess = readLine()!!.toInt()
        } while(guess != randNum)
    }
}