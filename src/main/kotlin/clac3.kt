fun calc3(v: String): Double = foldGroup(repMtoPM(trimAndCalc1(v)))

private fun trimAndCalc1(v: String): String {
    val result = trim(v)
    val bracketValue = getBracketValue(result)
    return result.replace(bracketValue, calc1(bracketValue).toString());
}

private val trim = """[^.\d-+*/()]""".toRegex()
private fun trim(v: String): String = v.replace(trim, "")

private val getBracket = """\([.\d+-]+\)""".toRegex()
private fun getBracketValue(v: String): String {
    var result = getBracket.find(v);
    if (result != null) {
        return result.value
    }
    return "";
}

private fun repMtoPM(v: String) = v.replace("-", "+-")

private val groupMD = """([*/]?)((?:\+|\+-)?[.\d]+)""".toRegex()

private fun foldGroup(v: String): Double = groupMD.findAll(v).fold(0.0) { acc, curr ->
    val (_, op, right) = curr.groupValues
    val rightValue = right.replace("+", "").toDouble()
    val result = when (op) {
        "*" -> acc * rightValue
        "/" -> acc / rightValue
        "" -> rightValue
        else -> throw Throwable("invalid operator $op")
    }
    result
}
