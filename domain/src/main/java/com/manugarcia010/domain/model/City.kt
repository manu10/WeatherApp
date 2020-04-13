package com.manugarcia010.domain.model

data class City (

	val id : Int,
	val name : String,
	val coord : Coord,
	val country : String,
	val population : Int,
	val timezone : Int
)