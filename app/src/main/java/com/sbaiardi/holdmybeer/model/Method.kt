package com.sbaiardi.holdmybeer.model

import com.google.gson.annotations.SerializedName


data class Method (

	@SerializedName("mash_temp") val mash_temp : List<Mash_temp>,
	@SerializedName("fermentation") val fermentation : Fermentation,
	@SerializedName("twist") val twist : String
)