class TODO(private val args : Array<String>) : Program {
    private var TODOs = mutableListOf<String>()

    init {
        if(args.size > 1){
            throw IllegalArgumentException("Too many arguments")
        }
    }
    override fun execute() {
        var userInput = if(args.isNotEmpty()) args[0] else ""

        while(userInput != "exit") {
            if(userInput != ""){
                TODOs.add(userInput)
            }
            println("Enter a TODO or exit command:")
            userInput = readLine()!!
        }

        println(TODOs.joinToString("\n"))
    }
}