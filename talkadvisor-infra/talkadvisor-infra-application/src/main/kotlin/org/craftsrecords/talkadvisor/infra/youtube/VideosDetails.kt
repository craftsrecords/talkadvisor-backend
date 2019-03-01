package org.craftsrecords.talkadvisor.infra.youtube

import java.time.Duration

internal class VideosDetails(val items: List<VideoDetails>)

internal class VideoDetails(val id: String, val contentDetails: ContentDetails)

internal class ContentDetails(val duration: Duration)
