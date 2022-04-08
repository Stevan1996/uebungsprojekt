class Calculator (private val args: Array<String>) : Program{
    init {
        if(args.size != 2 && args.isNotEmpty()) {
            throw IllegalArgumentException("Program expects exactly 0 or 2 arguments. Received ${args.size} argument(s)")
        }
    }

    override fun execute() {
        var numbers = mutableListOf<Double>()
        if(args.size == 2) {
            numbers = args.map { it.toDouble() }.toMutableList()
        } else {
            println("Type in first number:")
            numbers.add(readLine()!!.toDouble())

            println("Type in second number:")
            numbers.add(readLine()!!.toDouble())
        }

        println("${numbers[0]} + ${numbers[1]} = ${numbers.sum()}")
        println("${numbers[0]} - ${numbers[1]} = ${numbers[0] - numbers[1]}")
        println("${numbers[0]} * ${numbers[1]} = ${numbers[0] * numbers[1]}")
        println("${numbers[0]} : ${numbers[1]} = ${numbers[0] / numbers[1]}")
        println("${numbers[0]} % ${numbers[1]} = ${numbers[0] % numbers[1]}")
    }
}