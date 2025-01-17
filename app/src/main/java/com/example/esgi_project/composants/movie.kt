import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import coil.compose.rememberImagePainter
import com.example.esgi_project.api.MovieItem
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.esgi_project.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Context.dataStore by preferencesDataStore(name = "settings")

fun getRatingKey(movieId: Int) = intPreferencesKey("movie_rating_$movieId")

suspend fun saveRating(context: Context, movieId: Int, rating: Int) {
    context.dataStore.edit { preferences ->
        preferences[getRatingKey(movieId)] = rating
    }
}

fun getRating(context: Context, movieId: Int): Flow<Int> {
    return context.dataStore.data.map { preferences ->
        preferences[getRatingKey(movieId)] ?: 0
    }
}

@Composable
fun Movie(movie: MovieItem, isSaved: Boolean, onToggleSave: (String) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val rating by getRating(context, movie.id).collectAsState(initial = 0)

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
    ) {
        Image(
            painter = rememberImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}"),
            contentDescription = movie.title,
            modifier = Modifier.size(140.dp)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = movie.title, fontSize = 18.sp)
            Text(text = "Rating: ${movie.vote_average}", fontSize = 12.sp, color = Color.Gray)
            Text(text = movie.overview, fontSize = 12.sp, color = Color.Gray, overflow = TextOverflow.Ellipsis, maxLines = 2)

            // 5-star rating system
            Row(modifier = Modifier.padding(top = 8.dp)) {
                for (i in 1..5) {
                    Image(
                        painter = painterResource(if (i <= rating) R.drawable.star_filled else R.drawable.star_outlined),
                        contentDescription = "Star $i",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .clickable {
                                coroutineScope.launch {
                                    saveRating(context, movie.id, i)
                                }
                            }
                    )
                }
            }
            Button(
                onClick = { onToggleSave(movie.id.toString()) },
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isSaved) {
                    Icon(Icons.Default.Check, contentDescription = "Saved")
                    Text("Ajouté")
                } else {
                    Icon(Icons.Default.Add, contentDescription = "Save")
                    Text("Regarder plus tard")
                }
            }
        }
    }
}
