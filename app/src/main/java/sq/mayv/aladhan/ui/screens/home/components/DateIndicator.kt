package sq.mayv.aladhan.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sq.mayv.aladhan.R

@Composable
fun DateIndicator(
    date: String,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Card(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .size(40.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .size(40.dp),
                onClick = onPreviousClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Left Arrow",
                    tint = colorResource(id = R.color.primary)
                )
            }
        }

        Box(
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = date,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }

        Card(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .size(40.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .size(40.dp),
                onClick = onNextClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Right Arrow",
                    tint = colorResource(id = R.color.primary)
                )
            }
        }

    }
}