fun calc2(v: String): Double = calc(trimAndCalc1(v));

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

private fun trimAndCalc1(v: String): String {
    val result = trim(v)
    val bracketValue = getBracketValue(result)
    return result.replace(bracketValue, calc1(bracketValue).toString());
}
