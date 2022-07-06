package org.river.exertion.astro.render

import org.river.exertion.Constants

enum class RenderSignMode {
    CARDINAL_MODE { override fun getLabel() = Constants.SYM_CARDINAL_MODE }
    , FIXED_MODE { override fun getLabel() = Constants.SYM_FIXED_MODE }
    , MUTABLE_MODE { override fun getLabel() = Constants.SYM_MUTABLE_MODE }
    , NONE_MODE { override fun getLabel() = Constants.SYM_ASPECT_NONE } ;

    abstract fun getLabel(): String

    companion object {
        fun fromOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        fun fromName(name: String) = values().firstOrNull { it.name == name }
    }
}