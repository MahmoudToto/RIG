package com.example.rig.Model

import kotlinx.serialization.Serializable

@Serializable
data class PriceModel(
          var lost : String? = "",
          var plastic:String? = "",
          var glass:String? = "",
          var metal : String? = "",
          var paper:String? = "",
          var organic :String? ="",
          var oil:String? = ""
):java.io.Serializable
