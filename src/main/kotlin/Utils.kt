import kotlin.random.Random

class Utils {
    companion object {
        fun generateRandomYear(): Int {
            val min = 1000
            val max = 2999
            return (min..max).random()
        }

        fun getRandomInt(): Int {
            return (0..9999).random()
        }

        fun getRandomStr(): String {
                val maxLength: Int = 5
                val length = Random.nextInt(1, maxLength + 1)
                val chars = ('a'..'z') + ('A'..'Z')
                return (1..length)
                    .map { chars.random() }
                    .joinToString("")
        }
    }
}