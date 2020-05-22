package com.yun.maskdetector.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.yun.maskdetector.R

enum class RemainState(
    val value: String,
    @StringRes val stringId: Int,
    @ColorRes val colorId: Int
) {
    PLENTY(
        "plenty",
        R.string.remain_state_plenty,
        R.color.colorPrimary
    ),
    SOME(
        "some",
        R.string.remain_state_some,
        R.color.colorPrimary
    ),
    FEW(
        "few",
        R.string.remain_state_few,
        R.color.colorPrimary
    ),
    EMPTY(
        "empty",
        R.string.remain_state_empty,
        R.color.colorPrimary
    );

    companion object {
        fun getRemainState(value: String?): RemainState? {
            return values().find { it.value == value }
        }
    }
}