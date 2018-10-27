package com.ghozy19.footballapps.model.matchevent

import com.google.gson.annotations.SerializedName

data class ResponseEvent(

	@field:SerializedName("events")
	val events: List<EventsItem>
)