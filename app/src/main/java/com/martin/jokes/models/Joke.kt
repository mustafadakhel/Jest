package com.martin.jokes.models

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import com.martin.jokes.ui.main.vm.ColorPair
import kotlinx.parcelize.Parcelize

@Parcelize
data class Joke(
	@SerializedName("id")
	var id: Int = 0,
	@SerializedName("punchline")
	var punchline: String = "",
	@SerializedName("setup")
	var setup: String = "",
	@SerializedName("type")
	var type: String = "",
	var colorPair: ColorPair = ColorPair()
) : Parcelable {

	override fun toString(): String {
		return "$setup\n$punchline"
	}

	companion object {
		fun fromArgs(arguments: Bundle?): Joke {
			val backgroundColor = arguments?.getInt("backgroundColor") ?: 0
			val textColor = arguments?.getInt("textColor") ?: 0
			val colorPair = ColorPair(
				backgroundColor = Color(backgroundColor),
				textColor = Color(textColor)
			)
			return Joke(
				id = 0,
				setup = arguments?.getString("setup") ?: "",
				punchline = arguments?.getString("punch") ?: "",
				type = arguments?.getString("type") ?: "",
				colorPair = colorPair,
			)
		}
	}
}