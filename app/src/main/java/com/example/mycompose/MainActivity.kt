package com.example.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycompose.ui.theme.MyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    CreateBizCard()
                }
            }
        }
    }
}


@Composable
fun CreateBizCard(){
val buttonClickedState = remember {

    mutableStateOf(value = false)
}
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(12.dp)) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp), elevation = 4.dp,
            shape = RoundedCornerShape(corner = CornerSize(10.dp)), backgroundColor = Color.White) {


Column(modifier = Modifier.height(300.dp),
verticalArrangement = Arrangement.Top,
horizontalAlignment = Alignment.CenterHorizontally,) {
    ProfilePicCard()
    Divider(
        modifier = Modifier.padding(vertical = 0.dp, horizontal = 6.dp),
    )
    ContactInfoCard()
    Button(onClick = {
        buttonClickedState.value = !buttonClickedState.value
                     }, modifier = Modifier.padding(4.dp) ) {
        Text(text = "Portfolio", style = MaterialTheme.typography.button.copy(color = Color.White))
    }
    if (buttonClickedState.value){
        Content()
    }
}
        }
}
}


@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp,color= Color.LightGray)
        ) {
                Portfolio(data= listOf("Project 1", "Project 2", "Project 3", "Project 4"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{

        items(items = data, key = {it}  ) {
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),

                shape = RectangleShape
            ) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp),
                ) {
                 ProfilePicCard(modifier = Modifier.size(100.dp))
                    Column(modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(7.dp)) {
                        Text(text = "My $it", fontWeight = FontWeight.Bold)
                        Text(text = "A Great Project", style = MaterialTheme.typography.body2)

                    }
                }
            }
        }
    }
}

@Composable
private fun ContactInfoCard() {
    Column() {
        Text(
            text = "Tee Money",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(text = "Senior Software Engineer.", modifier = Modifier.padding(3.dp), color = Color.Black)
        Text(text = "+234 812 243 7265", style = MaterialTheme.typography.subtitle1, color = Color.Black)
    }
}

@Composable
private fun ProfilePicCard(modifier: Modifier=Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp), shape = CircleShape,
        border = BorderStroke(.05.dp, Color.Gray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(

            painter = painterResource(id = R.drawable.my_dash),
            contentDescription = "Profile Picture",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeTheme {
        CreateBizCard()
    }
}