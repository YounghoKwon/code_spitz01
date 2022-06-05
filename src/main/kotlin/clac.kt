fun calc(v: String) = foldGroup(repMtoPM(trim(v)))

private val trim = """[^.\d-+*/]""".toRegex()

private fun trim(v: String): String {
    return v.replace(trim, "")
}

private fun repMtoPM(v: String) = v.replace("-", "+-")

private val groupMD = """((?:\+|\+-)?[.\d]+)([*/])((?:\+|\+-)?[.\d]+)""".toRegex()

private fun foldGroup(v: String): Double = groupMD.findAll(v).fold(0.0) { acc, curr ->
    val (_, left, op, right) = curr.groupValues
    val leftValue = left.replace("+", "").toDouble()
    val rightValue = right.replace("+", "").toDouble()
    val result = when (op) {
        "*" -> leftValue * rightValue
        "/" -> leftValue / rightValue
        else -> throw Throwable("invalid operator $op")
    }
    acc + result
}
