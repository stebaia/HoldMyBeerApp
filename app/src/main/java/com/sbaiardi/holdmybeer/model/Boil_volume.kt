package com.sbaiardi.holdmybeer.model

import com.google.gson.annotations.SerializedName


data class Boil_volume (

	@SerializedName("value") val value : Double,
	@SerializedName("unit") val unit : String
)