package kotlinCode.dataStructures.stacks

//https://www.hackerrank.com/challenges/balanced-brackets

fun isBalanced(s: String): String {
    val stringLength = s.length

    if (stringLength % 2 != 0) {
        return "NO"
    }

    var originalString = s

    while (
        originalString.contains("()") ||
        originalString.contains("[]") ||
        originalString.contains("{}")
    ) {
        originalString = originalString
            .replace("()", "")
            .replace("[]", "")
            .replace("{}", "")
    }

    if (originalString.isEmpty()) {
        return "YES"
    }

    return "NO"
}

fun main() {
    println(isBalanced("{(([])[])[]}")) //YES
    println(isBalanced("{(([])[])[]]}")) //NO
    println(isBalanced("{(([])[])[]}[]")) //YES

}