package com.example.businesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardAppTheme {
                BusinessCard(
                    name = "Trần Trung Nghĩa",
                    jobTitle = "Mobile DeV - Home Company",
                    email = "Nghianee@gmail.com",
                    phone = "0123-456-789",
                    website = "http://www.f88.com",
                    facebook = "facebook.com/nghianee",
                    twitter = "twitter.com/nghianee",
                    github = "github.com/nghianee"
                )
            }
        }
    }
}

@Composable
fun BusinessCard(
    name: String,
    jobTitle: String,
    email: String,
    phone: String,
    website: String,
    facebook: String,
    twitter: String,
    github: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Canh giữa theo chiều ngang
        verticalArrangement = Arrangement.Center) // Canh giữa theo chiều dọc
    {
        // Ảnh đại diện
        Image(
            painter = painterResource(id = R.drawable._6),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop, // Cắt ảnh theo tỉ lệ hình tròn
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape) // Bo tròn ảnh
                .border(4.dp, Color(0xFF6666FF), CircleShape) // Viền ảnh
                .shadow(8.dp, CircleShape) // Đổ bóng
        )


        // Tên & chức danh
        Text(
            text = name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF003366),
            modifier = Modifier.padding(top = 12.dp, bottom = 10.dp)
        )

        Text(
            text = jobTitle,
            fontSize = 18.sp,
            color = Color(0xFF3366FF),
            fontWeight = FontWeight.Bold,    // Đậm
            modifier = Modifier.padding(top = 4.dp, bottom = 100.dp)
        )

        // Thông tin liên hệ
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .shadow(8.dp, shape = RoundedCornerShape(12.dp)) // Đổ bóng
                .background(Color.White, shape = RoundedCornerShape(12.dp)) // Bo góc
                .padding(16.dp)
        ) {
            ContactInfo("📧", email)
            ContactInfo("📞", phone)
            ContactInfo("🌐", website)
            ContactInfo("📘", facebook)
            ContactInfo("🐦", twitter)
            ContactInfo("🐱", github)
            // Nút "Contact Me"
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(Color(0xFF6666FF)),
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .align(Alignment.CenterHorizontally) // Canh giữa theo chiều ngang
                    .shadow(6.dp, RoundedCornerShape(50)) // Đổ bóng
            ) {
                Text("Contact Me", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

        }
    }
}

@Composable
fun ContactInfo(icon: String, text: String) {
    Row(modifier = Modifier.padding(4.dp)) {
        Text(text = icon, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold,color = Color(0xFF333333))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardAppTheme {
        BusinessCard(
            name = "Trần Trung Nghĩa",
            jobTitle = "Mobile DeV - Home Company",
            email = "Nghianee@gmail.com",
            phone = "0123-456-789",
            website = "http://www.f88.com",
            facebook = "facebook.com/nghianee",
            twitter = "twitter.com/nghianee",
            github = "github.com/nghianee"
        )
    }
}