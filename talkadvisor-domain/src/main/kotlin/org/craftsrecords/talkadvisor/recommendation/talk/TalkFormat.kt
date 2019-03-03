package org.craftsrecords.talkadvisor.recommendation.talk

import org.apache.commons.lang3.Validate.isTrue
import org.craftsrecords.talkadvisor.recommendation.duration.isInRange
import java.time.Duration
import java.time.Duration.ofHours
import java.time.Duration.ofMinutes

enum class TalkFormat(val format: String, val durationRange: ClosedRange<Duration>) {
    IGNITE("IGNITE", ofMinutes(1).rangeTo(ofMinutes(10).minusNanos(1))),
    QUICKIE("QUICKIE", ofMinutes(10).rangeTo(ofMinutes(20).minusNanos(1))),
    TOOL_IN_ACTION("TOOL_IN_ACTION", ofMinutes(20).rangeTo(ofMinutes(40).minusNanos(1))),
    CONFERENCE("CONFERENCE", ofMinutes(40).rangeTo(ofMinutes(60).minusNanos(1))),
    UNIVERSITY("UNIVERSITY", ofHours(1).rangeTo(ofHours(4)));

    companion object Converter {
        fun ofDuration(duration: Duration): TalkFormat {
            isTrue(lessThanTheMinimumDuration(duration), "Duration is less than expected")
            return values()
                    .singleOrNull { duration.isInRange(it.durationRange) }
                    ?: UNIVERSITY
        }

        private fun lessThanTheMinimumDuration(duration: Duration): Boolean {
            val firstRange = IGNITE.durationRange
            return duration.coerceIn(firstRange) != firstRange.start
        }
    }
}