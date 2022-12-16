enum class DaysOfTheWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY
}

fun main() {
    DaysOfTheWeek.values().forEach { println(it) }
}