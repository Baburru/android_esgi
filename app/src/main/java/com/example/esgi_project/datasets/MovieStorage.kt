import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Create a DataStore instance
private val Context.dataStore by preferencesDataStore(name = "saved_movies")

class MovieStorage(private val context: Context) {
    private val SAVED_MOVIES_KEY = stringSetPreferencesKey("saved_movies")

    // Flow of saved movie IDs
    val savedMovies: Flow<Set<String>> = context.dataStore.data
        .map { preferences -> preferences[SAVED_MOVIES_KEY] ?: emptySet() }

    // Add or remove movie ID
    suspend fun toggleMovie(movieId: String) {
        context.dataStore.edit { preferences ->
            val currentMovies = preferences[SAVED_MOVIES_KEY] ?: emptySet()
            preferences[SAVED_MOVIES_KEY] = if (currentMovies.contains(movieId)) {
                currentMovies - movieId
            } else {
                currentMovies + movieId
            }
        }
    }
}
