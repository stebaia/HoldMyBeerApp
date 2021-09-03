package com.sbaiardi.holdmybeer.model

import com.google.gson.annotations.SerializedName

data class Mash_temp (

	@SerializedName("temp") val temp : Temp,
	@SerializedName("duration") val duration : Int
)