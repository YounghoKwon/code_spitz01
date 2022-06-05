fun calc1(v: String) = foldGroup(repMtoPM(trim(v)))

private val trim = """[^.\d-+]""".toRegex()

private fun trim(v: String): String {
    return v.replace(trim, "")
}

private fun repMtoPM(v: String) = v.replace("-", "+-")
private val groupDigit = """((?:\+|\+-)?[.\d]+)""".toRegex()

private fun foldGroup(v: String): Double = groupDigit.findAll(v).fold(0.0) { acc, curr ->
    val (_, digit) = curr.groupValues
    val digitValue = digit.replace("+", "").toDouble()
    acc + digitValue
}
