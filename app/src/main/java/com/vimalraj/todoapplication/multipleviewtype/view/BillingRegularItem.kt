package com.vimalraj.todoapplication.multipleviewtype.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.IconType
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.ViewType

@Composable
fun BillingRegularItem(features: Features) {

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
                val iconType = IconType.valueOf(features.iconId?.name ?: "")
                Image(
                    modifier = Modifier
                        .padding(2.dp),
                    painter = painterResource(iconType.icon),
                    contentDescription = iconType.contentDescription,
                )
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp)
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
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(R.color.white),
                    containerColor = colorResource(R.color.red30)
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
fun PreviewBilling() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.grey))
    ) {
        BillingRegularItem(
            features = Features(
                featureId = "BILLING",
                title = "Effortless Bill Management",
                description = "Quickly access and review your bills in one place. Make payments securely and conveniently with just a few clicks.",
                viewType = ViewType.BILLING_REGULAR,
                buttonText = "View and Pay bills",
            )
        )
    }
}