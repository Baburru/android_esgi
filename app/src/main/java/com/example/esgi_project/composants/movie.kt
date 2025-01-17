package com.example.esgi_project.composants

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.esgi_project.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.em
import coil.compose.rememberImagePainter
import com.example.esgi_project.api.MovieItem

@Composable
fun Movie(movie: MovieItem) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
    ) {
        Image(
            painter = rememberImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
            contentDescription = movie.title,
            modifier = Modifier.size(100.dp)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = movie.title, fontSize = 18.sp)
            Text(text = "Rating: ${movie.vote_average}", fontSize = 12.sp, color = Color.Gray)
            Text(text = movie.overview, fontSize = 12.sp, color = Color.Gray)
        }
    }
}