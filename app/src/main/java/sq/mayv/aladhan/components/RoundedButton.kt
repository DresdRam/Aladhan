package sq.mayv.aladhan.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sq.mayv.aladhan.R

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 22,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = colorResource(id = R.color.primary),
        contentColor = Color.White
    ),
    onClicked: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = colors,
        shape = RoundedCornerShape(10.dp),
        onClick = { onClicked() }
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp
        )
    }
}