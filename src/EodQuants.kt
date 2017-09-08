import java.io.Serializable

class EodQuants {

    var date: Int = 0
    var unknown: Int = 0
    var open: Float = 0.toFloat()
    var high: Float = 0.toFloat()
    var low: Float = 0.toFloat()
    var close: Float = 0.toFloat()
    var volume: Float = 0.toFloat()

    override fun toString(): String {
        return "[$date,$open,$high,$low,$close,$volume]"
    }
}
