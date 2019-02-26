package org.craftsrecords.talkadvisor

import java.nio.charset.Charset
import kotlin.random.Random

fun Random.nextString() = String(Random.nextBytes(4), Charset.defaultCharset())