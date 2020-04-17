package kotlinCode.dictionariesAndHashmaps

//https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem

fun main() {
    print(sherlockAndAnagrams("abba"))
}

fun sherlockAndAnagrams(s: String): Int {
    var result = 0

    fun countDiffAnagrams(words: List<String>): Map<String, Int> {
        return words.groupingBy { it.toCharArray().sorted().toString() }.eachCount()
    }

    fun countPairs(numbers: Collection<Int>): Int {
        var pairs = 0;
        for (number in numbers) {
            if (number >= 2) {
                pairs += number * (number - 1) / 2
            }
        }
        return pairs
    }

    for (size in 1 until s.length) {
        // mount all combinations of `size` consecutive letters from string s
        val indexes = s.indices.toList().subList(0, s.length - (size - 1))
        val listOfWords = mutableListOf<String>()
        for (startingPoint in indexes) {
            listOfWords.add(s.substring(startingPoint, startingPoint + size))
        }
        val anagramCounter = countDiffAnagrams(listOfWords)
        result += countPairs(anagramCounter.values)
    }
    return result
}