package machine

fun main() {
    val coffeeMachine1 = CoffeeMachine()
    coffeeMachine1.selectAction()
}

enum class CoffeeType(val water: Int, val milk: Int, val coffeeBean: Int, val money: Int) {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6)
}

data class CoffeeMachine(
    var water: Int = 400,
    var milk: Int = 540,
    var coffeeBean: Int = 120,
    var cup: Int = 9,
    var money: Int = 550
) {
    private fun fillResources(resources: MutableList<Int>) {
        water += resources[0]
        milk += resources[1]
        coffeeBean += resources[2]
        cup += resources[3]
    }

    fun selectAction() {
        do {
            println("Write action (buy, fill, take, remaining, exit): ")
            val action = readln()
            when (action) {
                "buy" -> {
                    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                    val coffeeChoice = readln()
                    if (coffeeChoice != "back") {
                        selectCoffeeType(coffeeChoice.toInt())
                    }
                }

                "fill" -> fillResources(inputResources())
                "take" -> {
                    println("I gave you \$${money}")
                    money = 0
                }

                "remaining" -> println(statusCoffeeMachine())
            }
        } while (action != "exit")
    }

    private fun buyCoffee(type: CoffeeType) {
        println(checkResources(type))
        if (checkResources(type) == "I have enough resources, making you a coffee!") {
            water -= type.water
            milk -= type.milk
            coffeeBean -= type.coffeeBean
            cup--
            money += type.money
        }
    }

    private fun checkResources(type: CoffeeType): String {

        return if (type.water > water) {
            "Sorry, not enough water!"
        } else if (type.milk > milk) {
            "Sorry, not enough milk!"
        } else if (type.coffeeBean > coffeeBean) {
            "Sorry, not enough coffee beans!"
        } else if (cup < 1) {
            "Sorry, not enough cup!"
        } else "I have enough resources, making you a coffee!"
    }


    private fun inputResources(): MutableList<Int> {
        val resource = mutableListOf<Int>()
        println("Write how many ml of water you want to add: ")
        resource.add(readln().toInt())
        println("Write how many ml of milk you want to add: ")
        resource.add(readln().toInt())
        println("Write how many grams of coffee beans you want to add: ")
        resource.add(readln().toInt())
        println("Write how many disposable cups you want to add: ")
        resource.add(readln().toInt())
        return resource
    }

    private fun selectCoffeeType(coffeeChoice: Int) {

        when (coffeeChoice) {
            1 -> buyCoffee(CoffeeType.ESPRESSO)

            2 -> buyCoffee(CoffeeType.LATTE)

            3 -> buyCoffee(CoffeeType.CAPPUCCINO)
        }
    }

    private fun statusCoffeeMachine(): String {
        return "The coffee machine has:\n" +
                "$water ml of water\n" +
                "$milk ml of milk\n" +
                "$coffeeBean g of coffee beans\n" +
                "$cup disposable cups\n" +
                "\$$money of money"
    }
}
