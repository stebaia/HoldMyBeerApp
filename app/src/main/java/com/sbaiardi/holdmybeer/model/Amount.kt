package com.sbaiardi.holdmybeer.model

import com.google.gson.annotations.SerializedName

data class Amount (

	@SerializedName("value") val value : Double,
	@SerializedName("unit") val unit : String
)