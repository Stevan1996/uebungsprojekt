class LeapYearChecker(private val args : Array<String>) : Program {
    init{
        if(args.isNotEmpty() && args.size != 1){
            throw IllegalArgumentException("1 or 0 arguments expected. Received ${args.size} argument(s)")
        }
    }

    private fun isLeapYear(year : Int) : Boolean {
        return year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0)
    }

    override fun execute() {
        val year = if(args.size == 1){
            args[0].toInt()
        } else {
            readLine()!!.toInt()
        }

        val msg = if(isLeapYear(year)) "$year is a leap year" else "$year is not a leap year"
        println(msg)
    }
}