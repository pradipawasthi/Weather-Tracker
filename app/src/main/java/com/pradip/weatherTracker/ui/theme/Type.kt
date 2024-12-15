package com.pradip.weatherTracker.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pradip.weatherTracker.R

val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal) // Ensure you have added Poppins font in res/font
)

val poppinsRegularFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal)
)

val poppinsBoldFontFamily = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
)
val poppinsSemiBoldFontFamily = FontFamily(
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
)
val poppinsMediumFontFamily = FontFamily(
    Font(R.font.poppins_medium, FontWeight.Medium),
)


val Typography = WeatherTrackerTypography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = poppinsRegularFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.5.sp,
    ),
    wtMedium = TextStyle(
            fontFamily = poppinsSemiBoldFontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 30.sp,
    lineHeight = 45.sp,
),

    wtBoldLabel = TextStyle(
        fontFamily = poppinsSemiBoldFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 22.5.sp,
    ),

    wtStatsTitle =  TextStyle(
            fontFamily = poppinsMediumFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 18.sp,
),

    wtTitle1 =  TextStyle(
        fontFamily = poppinsMediumFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 70.sp,
        lineHeight = 105.sp,
    ),


    wtFeelsLikeTitle =  TextStyle(
            fontFamily = poppinsMediumFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 8.sp,
    lineHeight = 12.sp,
),
    wtSemiBoldTitle = TextStyle(
        fontFamily = poppinsMediumFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        lineHeight = 45.sp,
    ),





    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)