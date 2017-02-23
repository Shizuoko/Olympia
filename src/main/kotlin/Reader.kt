package reader

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * Created by Harmitage on 19.08.2016.
 */
object Reader {
    val JSON = jacksonObjectMapper()
    val JSONFactory = JsonFactory()
}
