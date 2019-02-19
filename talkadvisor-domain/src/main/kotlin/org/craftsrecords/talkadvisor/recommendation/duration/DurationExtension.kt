package org.craftsrecords.talkadvisor.recommendation.duration

import java.time.Duration

fun Duration.isPositive() = !(this.isZero || this.isNegative)
fun Duration.isInRange(range: ClosedRange<Duration>) = this.coerceIn(range) == this