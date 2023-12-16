package com.shankarlohar.teamvinayak.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.Notification

@Preview
@Composable
fun NotificationCard(
    notification: Notification = Notification(
        title = "Sample",
        description = "This is a sample notification This is a sample notification This is a sample notification This is a sample notification This is a sample notification This is a sample notification This is a sample notification",
        from = "Ankit Shaw",
        time = "14:30",
        date = "16/12/2020"
    )
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = {

            }),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {


            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = notification.title,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = notification.description,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp).fillMaxWidth(.65f),
                )

                Row(verticalAlignment = Alignment.Bottom) {

                    val location: Painter = painterResource(id = R.drawable.baseline_calendar_today_24)

                    Icon(
                        painter = location,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp, 16.dp),
                        tint = Color.Red
                    )

                    Text(
                        text = buildString {
                            append(notification.date)
                            append(" - ")
                            append(notification.time)
                        },
                        modifier = Modifier.padding(8.dp, 12.dp, 12.dp, 0.dp),
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                FromTag(notification.from)
            }
        }
    }
}

@Composable
fun FromTag(name: String) {
    val color = if (name == "Male") Color.Blue else Color.Red
    ChipView(from = name, colorResource = color)
}

@Composable
fun ChipView(from: String, colorResource: Color) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource.copy(.08f))
    ) {
        Text(
            text = from, modifier = Modifier.padding(12.dp, 6.dp, 12.dp, 6.dp),
            color = colorResource
        )
    }
}