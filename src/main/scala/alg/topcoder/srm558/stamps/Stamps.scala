package alg.topcoder.srm558.stamps

object Stamp {
  type Col = Option[Int] // 0,1,2
}

object Util {
  def minOption[A : Ordering](as: Seq[Option[A]]): Option[A] = {
    val xs = as.flatten
    if (xs.isEmpty) None
    else Some(xs.min)
  }
}

class Stamp(L: Int, cols: Seq[Stamp.Col]) {
  import Stamp._
  import Util._

  private val colors = Seq(0, 1, 2)

  private def checkAt(i: Int, col: Int): Boolean = 
    i + L <= cols.size && cols.slice(i, i+L).forall(_ map(_ == col) getOrElse true) 

  private val memo = collection.mutable.Map[(Int, Int), Option[Int]]()

  def minCountAtMemo(i: Int, col: Int): Option[Int] = {
    val key = (i, col)
    if (memo contains key) memo(key)
    else {
      val value = minCountAt(i, col)
      memo((i, col)) = value
      value
    }
  }

  def minCountAt(i: Int, col: Int): Option[Int] = {
    if (!checkAt(i, col)) None
    else if (i == 0) Some(1)
    else {
      // full stamp shifts, try any colors
      val fullShift = 
        if (i - L < 0) None 
        else minOption(colors.map(minCountAtMemo(i - L, _)))
      
      // partial shifts with same color
      val partials = ((i - L) max 0).to(i - 1).map(minCountAt(_, col))
      
      minOption(fullShift +: partials) map(_ + 1)
    }
  }

  def minCount: Option[Int] =
    minOption(colors.map(minCountAtMemo(cols.size - L, _)))

}

case class Result(stampCount: Int, width: Int)

class StampTask(stampCost: Int, pushCost: Int) {

  def parse(cs: String): Seq[Stamp.Col] =
    cs.map { 
      case '*' => None
      case 'R' => Some(0)
      case 'G' => Some(1)
      case 'B' => Some(2)
    }

  private def resultCost(r: Result) =
    r.width * stampCost + r.stampCount * pushCost

  def run(cs: String): Int = {
    val cols = parse(cs)
    val countsByWidth = (1 to 50).map{ L =>
      new Stamp(L, cols).minCount map (Result(_, L))
    }.flatten

    resultCost(countsByWidth.minBy(resultCost))
  }

}
