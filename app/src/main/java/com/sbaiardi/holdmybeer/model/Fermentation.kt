package com.sbaiardi.holdmybeer.model


import com.google.gson.annotations.SerializedName


data class Fermentation (

	@SerializedName("temp") val temp : Temp
)