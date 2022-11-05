package com.example.watchnowanime.model

data class EpisodeStream(
    val Referer: String,
    val sources: List<Source>,
    val sources_bk: List<SourcesBk>
)