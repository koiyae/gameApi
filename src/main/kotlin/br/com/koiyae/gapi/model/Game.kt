package br.com.koiyae.gapi.model

data class Game(
    val title: String?, val thumb: String?
) {

    var description: String? = null

    override fun toString(): String {
        return "Meu jogo: \nTítulo: $title\nCapa: $thumb\nDescrição: $description\n"
    }
}