package com.example.rig.Model

import kotlinx.serialization.Serializable

@Serializable
data class PriceModel(
    var metal: String? = "",
    var organic: String? = "",
    var paper: String? = "",
    var plastic: String? = "",
    var glass: String? = "",
    var oil: String? = ""
) : java.io.Serializable


@Serializable
data class WeightModel(
    var metal: Double? = 0.0,
    var organic: Double? = 0.0,
    var paper: Double? = 0.0,
    var plastic: Double? = 0.0,
    var glass: Double? = 0.0,
    var oil: Double? = 0.0
) : java.io.Serializable

@Serializable
data class PricntageModel(
    var metal: Int? = 0,
    var organic: Int? = 0,
    var paper: Int? = 0,
    var plastic: Int? = 0,
    var glass: Int? = 0,
    var oil: Int? = 0,
) : java.io.Serializable