package az.edu.bhos.l14todoapp.data

import android.util.Log
import az.edu.bhos.l14todoapp.data.api.TodoAPI
import az.edu.bhos.l14todoapp.data.dto.TodoRemoteDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
interface TodoRemoteData {
    suspend fun getTodos(): List<TodoRemoteDto>
}

class TodoRemoteDataImpl : TodoRemoteData {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://6607bbf2a2a5dd477b1355dd.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: TodoAPI = retrofit.create(TodoAPI::class.java)

    override suspend fun getTodos(): List<TodoRemoteDto> {
        return try {
            api.getTodos()
        } catch (ex: Exception) {
            Log.e("NETWORK", ex.toString())
            emptyList()
        }
    }
}