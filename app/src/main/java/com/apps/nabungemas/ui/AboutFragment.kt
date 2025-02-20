package com.apps.nabungemas.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apps.nabungemas.BuildConfig
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.ui.theme.MyApplicationTheme


@Composable
fun AboutScreen(){
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "About",
                version = 2,
                navigateUp = {})
        },
        backgroundColor = Color(0xFFF4F9FB)
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size(120.dp),
                painter = painterResource(id = R.drawable.ic_saving_gold),
                contentDescription = ""
            )
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 10.dp))
                    .background(color = colorResource(id = R.color.orange))
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                    color = Color.White

                    )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.about_statement),
                style = MaterialTheme.typography.body2.copy(textAlign = TextAlign.Center)
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.version,BuildConfig.VERSION_NAME),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Center)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text =
                stringResource(id = R.string.copyright),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Center)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AboutPreview(){
    MyApplicationTheme(darkTheme = false) {
        AboutScreen()
    }
}