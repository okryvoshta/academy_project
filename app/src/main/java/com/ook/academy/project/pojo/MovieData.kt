package com.ook.academy.project.pojo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class MovieData(
    @DrawableRes val coverId: Int,
    @StringRes val nameId: Int,
    @StringRes val tagId: Int,
)