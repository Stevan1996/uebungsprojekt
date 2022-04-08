class WeatherStation(private val args : Array<String>) : Program {
    private val weatherData = mutableListOf<Int>()
    init {
        if(args.size > 14){
            throw IllegalArgumentException("Too many arguments")
        }
    }

    override fun execute() {
        for(arg in args){
            weatherData.add(arg.toInt())
        }

        while(weatherData.size < 14){
            println("Weather temperature for Day ${weatherData.size}:")
            weatherData.add(readLine()!!.toInt())
        }

        println("Max temperature: ${weatherData.maxOrNull()}")
        println("Min temperature: ${weatherData.minOrNull()}")
        println("Average temperature: ${weatherData.average()}")
    }
}