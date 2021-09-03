package com.sbaiardi.holdmybeer.model

import com.google.gson.annotations.SerializedName


data class Temp (

	@SerializedName("value") val value : Int,
	@SerializedName("unit") val unit : String
)