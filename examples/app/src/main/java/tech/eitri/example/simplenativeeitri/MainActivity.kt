package tech.eitri.example.simplenativeeitri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import tech.calindra.eitri.android.publicModels.RunInput
import tech.eitri.example.simplenativeeitri.service.MainAppService
import tech.eitri.example.simplenativeeitri.ui.theme.SimpleNativeEitriTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleNativeEitriTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            MainAppService.getInstance().eitriService.runOnTop(
                                runInput = RunInput(
                                    context = this@MainActivity,
                                    slug = "eitri-doctor"
                                )
                            )
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA3D941)),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(text = "eitri-doctor", color = Color.White)
                    }
                }
            }
        }
    }
}