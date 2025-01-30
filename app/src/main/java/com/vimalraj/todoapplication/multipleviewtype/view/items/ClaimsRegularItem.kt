package com.vimalraj.todoapplication.multipleviewtype.view.items

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun ClaimsRegularItem(features: Features) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white),
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(0.5f)
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
                }

                val iconType = IconType.valueOf(features.iconId?.name ?: "")
                Image(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(80.dp),
                    painter = painterResource(iconType.icon),
                    contentDescription = iconType.contentDescription,
                )
            }

            Button(
                onClick = {},
                shape = RoundedCornerShape(23.dp),
                border = BorderStroke(2.dp, color = colorResource(R.color.dark_blue)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(R.color.white),
                    containerColor = colorResource(R.color.fade_dark_blue)
                ),
            ) {
                Text(
                    getValueOrEmpty(features.buttonText),
                    modifier = Modifier.padding(all = 8.dp),
                    fontSize = 16.sp
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewClaims() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.grey))
    ) {
        ClaimsRegularItem(
            features = Features(
                featureId = "BILLING",
                iconId = IconType.SUBMIT_A_CLAIM,
                title = "Effortless Bill Management",
                description = "Quickly access and review your bills in one place. Make payments securely and conveniently with just a few clicks.",
                viewType = ViewType.CLAIM_REGULAR,
                buttonText = "View and Pay bills",
            )
        )
    }
}