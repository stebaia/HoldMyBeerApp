package com.sbaiardi.holdmybeer.model

import com.google.gson.annotations.SerializedName


data class Beer (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("tagline") val tagline : String,
	@SerializedName("first_brewed") val first_brewed : String,
	@SerializedName("description") val description : String,
	@SerializedName("image_url") val image_url : String,
	@SerializedName("abv") val abv : Double,
	@SerializedName("ibu") val ibu : Double,
	@SerializedName("target_fg") val target_fg : Double,
	@SerializedName("target_og") val target_og : Double,
	@SerializedName("ebc") val ebc : Double,
	@SerializedName("srm") val srm : Double,
	@SerializedName("ph") val ph : Double,
	@SerializedName("attenuation_level") val attenuation_level : Double,
	@SerializedName("volume") val volume : Volume,
	@SerializedName("boil_volume") val boil_volume : Boil_volume,
	@SerializedName("method") val method : Method,
	@SerializedName("ingredients") val ingredients : Ingredients,
	@SerializedName("food_pairing") val food_pairing : List<String>,
	@SerializedName("brewers_tips") val brewers_tips : String,
	@SerializedName("contributed_by") val contributed_by : String
)