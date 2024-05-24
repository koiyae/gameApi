package br.com.koiyae.gapi.services

import com.google.gson.Gson
import br.com.koiyae.gapi.model.Info
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumeApi {

    fun searchGame(id: String): Info {
        val address = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder().uri(URI.create(address)).build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()

        val gson = Gson()
        val myInfoGame = gson.fromJson(json, Info::class.java)

        return myInfoGame
    }
}
