package com.ghozy19.footballapps.model

import com.ghozy19.footballapps.model.matchevent.EventsItem
import com.google.gson.annotations.SerializedName

data class EventsSearchReponse(

        @field:SerializedName("event")
        val event: List<EventsItem>
)