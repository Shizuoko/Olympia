import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * Created by Shizuoko on 28.02.2017.
 */

object Reader {
    val JSON = jacksonObjectMapper()
    val JSONFactory = JsonFactory()
}