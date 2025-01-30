package com.vimalraj.todoapplication.multipleviewtype.view.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.multipleviewtype.data.Features
import com.vimalraj.todoapplication.multipleviewtype.view.getValueOrEmpty
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.IconType
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.ViewType

@Composable
fun VCSRegularItem(features: Features) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white),
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .padding(horizontal = 24.dp)
                    .padding(vertical = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(all = 4.dp),
                    text = getValueOrEmpty(features.title),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(all = 4.dp),
                    text = getValueOrEmpty(features.description),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    getValueOrEmpty(features.buttonText),
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                    fontSize = 16.sp,
                    color = colorResource(R.color.dark_blue),
                    fontWeight = FontWeight.SemiBold
                )
            }

            val iconType = IconType.valueOf(features.iconId?.name ?: "")
            Image(
                modifier = Modifier
                    .padding(2.dp)
                    .size(100.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(iconType.icon),
                contentDescription = iconType.contentDescription,
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewVCS() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.grey))
    ) {
        VCSRegularItem(
            features = Features(
                featureId = "INSURANCE_INFO",
                iconId = IconType.INSURANCE_INFO,
                title = "Insurance Coverage Information",
                description = "Understand how much of the visit cost is covered by your insurance and what portion is your responsibility",
                viewType = ViewType.VCS_REGULAR,
                buttonText = "Insurance Info",
            )
        )
    }
}