package com.vimalraj.todoapplication.multipleviewtype.view.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.multipleviewtype.data.Features
import com.vimalraj.todoapplication.multipleviewtype.view.getValueOrEmpty
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.IconType
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.ViewType

@Composable
fun InvoiceConstraintItem(features: Features) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white),
        )
    ) {

        ConstraintLayout(modifier = Modifier.padding(all = 16.dp)) {
            val (titleText, descriptionText, subFeatureList) = createRefs()

            Text(
                modifier = Modifier.constrainAs(titleText) {
                    top.linkTo(parent.top, margin = 8.dp)
                },
                text = getValueOrEmpty(features.title),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier.constrainAs(descriptionText) {
                    top.linkTo(titleText.bottom, margin = 8.dp)
                },
                text = getValueOrEmpty(features.description),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Normal
            )

            LazyRow(modifier = Modifier
                .constrainAs(subFeatureList) {
                    top.linkTo(descriptionText.bottom, margin = 8.dp)
                }) {
                itemsIndexed(
                    features.subFeatureButtonsList ?: emptyList()
                ) { _: Int, subFeature: String ->

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            contentColor = colorResource(R.color.white),
                            containerColor = colorResource(R.color.green)
                        ),
                        onClick = {
                        }, modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 4.dp)
                    ) {
                        Text(subFeature, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewInvoice() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.grey))
    ) {
        InvoiceConstraintItem(
            features = Features(
                featureId = "INVOICE",
                iconId = IconType.SUBMIT_A_CLAIM,
                title = "Invoice Generation",
                description = "Invoice Generation automates the creation and delivery of accurate, timely, and professionally formatted invoices to streamline billing processes and improve cash flow.",
                viewType = ViewType.INVOICE,
                buttonText = "Any",
                subFeatureButtonsList = listOf(
                    "Customizable Templates",
                    "Automated Invoicing",
                    "Recurring Billing"
                )
            )
        )
    }
}