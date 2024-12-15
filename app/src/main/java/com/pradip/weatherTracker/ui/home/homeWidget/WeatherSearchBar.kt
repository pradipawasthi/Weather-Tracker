package com.pradip.weatherTracker.ui.home.homeWidget

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pradip.weatherTracker.ui.theme.SearchBarColor
import com.pradip.weatherTracker.ui.theme.Typography
import com.pradip.weatherTracker.ui.theme.SearchTextColor

@Composable
fun WeatherSearchBar(
    searchText: () -> String,
    onSearchTextChanged: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = SearchBarColor,
        modifier = modifier.height(48.dp).padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                BasicTextField(
                    value = searchText(),
                    onValueChange = onSearchTextChanged,
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    decorationBox = { innerTextField ->
                        if (searchText().isEmpty()) {
                            Text(
                                text = "Search Location",
                                fontSize = 14.sp,
                                style = Typography.labelSmall,
                                color = SearchTextColor,
                            )
                        }
                        innerTextField()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            AnimatedContent(targetState = searchText().isBlank()) { isEmpty ->
                if (isEmpty)
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = SearchTextColor,
                    modifier = Modifier.size(18.dp)
                )
                else {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Icon",
                        tint = SearchTextColor,
                        modifier = Modifier.size(18.dp).clickable {
                            onClear()
                        },
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun WeatherSearchBarPreview(){
    WeatherSearchBar(
        searchText = {
            ""
        },
        onSearchTextChanged = {},
        onClear = {},
        modifier=  Modifier
    )
}
