package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.Size
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val firstArtwork = R.drawable.autorretrato_vangogh
    val secondArtwork = R.drawable.entierrocondeorgaz
    val thirdArtwork = R.drawable.gioconda
    val fourthArtwork = R.drawable.girasoles


    var title by remember {
        mutableStateOf(R.string.autorretrato_van_gogh)
    }

    var year by remember {
        mutableStateOf(R.string.autorretrato_van_gogh_year)
    }

    var currentArtwork by remember {
        mutableStateOf(firstArtwork)
    }

    var imageResource by remember {
        mutableStateOf(currentArtwork)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()) ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkDisplay(currentArtwork = currentArtwork)
        Spacer(modifier = modifier.size(100.dp))
        ArtworkTitle(title = title, year = year)
        Spacer(modifier = modifier.size(70.dp))
        Row(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Previous
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.girasoles
                            year = R.string.girasoles_year
                        }

                        secondArtwork -> {
                            currentArtwork = firstArtwork
                            title = R.string.autorretrato_van_gogh
                            year = R.string.autorretrato_van_gogh_year
                        }

                        thirdArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.entierro_conde_orgaz
                            year = R.string.entierro_conde_orgaz_year
                        }

                        else -> {
                            currentArtwork = thirdArtwork
                            title = R.string.gioconda
                            year = R.string.gioconda_year
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.gray_900)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }

            // Next Button
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.entierro_conde_orgaz
                            year = R.string.entierro_conde_orgaz_year
                        }

                        secondArtwork -> {
                            currentArtwork = thirdArtwork
                            title = R.string.gioconda
                            year = R.string.gioconda_year
                        }

                        thirdArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.girasoles
                            year = R.string.girasoles_year
                        }

                        else -> {
                            currentArtwork = firstArtwork
                            title = R.string.autorretrato_van_gogh
                            year = R.string.autorretrato_van_gogh_year
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorResource(id = R.color.gray_900)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }

        }
    }
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes year: Int
) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFFECEBF4))
            .shadow(1.dp)
            .padding(5.dp)
    ) {
        Text(
            text = stringResource(id = title),
            fontSize = 22.sp
        )
        Row {
            Text(text = "Fecha ", fontWeight = FontWeight.Bold)
            Text(
                text = "(${stringResource(id = year)})",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.gray_300)
            )
        }

    }
}

@Composable
fun ArtworkDisplay(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {

    Box(
        modifier = Modifier
            .shadow(20.dp, clip = true)
    ) {

        Image(
            painter = painterResource(currentArtwork),
            contentDescription = stringResource(id = R.string.girasoles),
            modifier = modifier
                .width(450.dp)
                .height(450.dp)
                .background(color = Color.White)
                .padding(20.dp),
            contentScale = ContentScale.Fit
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}