package com.ncgr.maqsaf.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val dinNextLTArabicRegularTextStyle =
    TextStyle(fontFamily = DinNextLTArabic, fontWeight = FontWeight.Normal)
val dinNextLTArabicMediumTextStyle =
    TextStyle(fontFamily = DinNextLTArabic, fontWeight = FontWeight.Medium)
val dinNextLTArabicBoldTextStyle =
    TextStyle(fontFamily = DinNextLTArabic, fontWeight = FontWeight.Bold)

val TitleTextStyle = dinNextLTArabicBoldTextStyle.copy(fontSize = 28.sp)
val BodyTextStyle = dinNextLTArabicMediumTextStyle.copy(fontSize = 14.sp)
val DescriptionTextStyle = dinNextLTArabicRegularTextStyle.copy(fontSize = 14.sp)
val ButtonTextStyle = dinNextLTArabicMediumTextStyle.copy(fontSize = 16.sp)
val ErrorTextStyle = dinNextLTArabicRegularTextStyle.copy(fontSize = 14.sp)

val TextFieldTextStyle =
    dinNextLTArabicRegularTextStyle.copy(fontSize = 18.sp, textAlign = TextAlign.End)