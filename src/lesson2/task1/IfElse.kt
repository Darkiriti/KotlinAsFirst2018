@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    if ((age % 100 > 10) && (age % 100 < 20)) return "$age лет"
    when (age % 10){
        1 -> return "$age год"
        2, 3, 4 -> return "$age года"
        else -> return "$age лет"
    }
}


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val s = (v1 * t1 + v2 * t2 + v3 * t3) / 2
    if (s <= v1 * t1) return s / v1
    if (s <= v1 * t1 + v2 * t2) return t1 + (s - v1 * t1) / v2
    else return t1 + t2 + (s - v1 * t1 - v2 * t2) / v3
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    var a = 0
    var b = 0
    if (kingX == rookX1) a++
    if (kingX == rookX2) b++
    if (kingY == rookY1) a++
    if (kingY == rookY2) b++
    if ((a > 0) && (b > 0)) return 3
    else if (a > b) return 1
    else if (b > a) return 2
    else return 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val a = (kingX - bishopX) * (kingX - bishopX)
    val b = (kingY - bishopY) * (kingY - bishopY)
    var x = 0
    var y = 0
    if (a == b) x++
    if ((kingX == rookX) || (kingY == rookY)) y++
    if ((x > 0) && (y > 0)) return 3
    if (x > y) return 2
    if (y > x) return 1
    else return 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if ((a + b <= c) || (a + c <= b) || (b + c <= a)) return -1
    else {
        val cos1 = (a * a + b * b - c * c) / (2 * a * b)
        val cos2 = (a * a - b * b + c * c) / (2 * a * c)
        val cos3 = (-a * a + b * b + c * c) / (2 * b * b)
        if ((cos1 == 0.0) || (cos2 == 0.0) || cos3 == 0.0) return 1
        if ((cos1 < 0) || (cos2 < 0) || (cos3 < 0)) return 2
        else return 0
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (a < c) {
        if ((b >= c) && (b <= d)) return b - c
        if ((b >= c) && (b > d)) return d - c
        else return -1
    }
    if (a > c) {
        if ((d >= a) && (d <= b)) return d - a
        if ((d >= a) && (d > b)) return b - a
        else return -1
    }
    if (a == c)
        if (b >= d) return d - a
        else return b - a
    else return 0
}
