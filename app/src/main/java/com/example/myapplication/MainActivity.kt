package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---------- Модель данных ----------
data class User(
    val name: String,
    val profession: String,
    val projects: Int,
    val tasks: Int,
    val years: Int
)

val users = listOf(
    User("Бектемир Абилов", "Android-разработчик", 12, 48, 5),
    User("Айгерим Сатыбалдиева", "UI/UX-дизайнер", 20, 95, 7),
    User("Бекзат Исаков", "Backend-разработчик", 8, 33, 3)
)

// ---------- Activity ----------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                UsersScreen()
            }
        }
    }
}

// ---------- Экран со списком карточек (доп. задание) ----------
@Composable
fun UsersScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            users.forEach { user ->
                ProfileCard(user)
            }
        }
    }
}

// ---------- Карточка профиля ----------
@Composable
fun ProfileCard(user: User) {
    Column {
        // Box с фоном
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFEEF2FF))
                .padding(16.dp)
        ) {
            // Внутри Box — Column, чтобы три Row шли друг под другом, а не накладывались
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                // 1) Аватар + имя и профессия
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF3F51B5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = user.name.first().toString(),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Column {
                        Text(
                            text = user.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A2E)
                        )
                        Text(
                            text = user.profession,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    }
                }

                // 2) Статистика: три блока одинаковой ширины
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .padding(vertical = 12.dp)
                ) {
                    StatItem(Modifier.weight(1f), user.projects.toString(), "проектов")
                    StatItem(Modifier.weight(1f), user.tasks.toString(), "задач")
                    StatItem(Modifier.weight(1f), user.years.toString(), "лет опыта")
                }

                // 3) Две кнопки
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Написать")
                    }
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Подписаться")
                    }
                }
            }
        }
    }
}

// ---------- Один блок статистики ----------
@Composable
fun StatItem(modifier: Modifier, value: String, label: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3F51B5)
        )
        Text(
            text = label,
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}

// ---------- Превью ----------
@Preview(showBackground = true)
@Composable
fun UsersScreenPreview() {
    MaterialTheme {
        UsersScreen()
    }
}