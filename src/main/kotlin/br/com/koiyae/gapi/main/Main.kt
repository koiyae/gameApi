package br.com.koiyae.gapi.main

import br.com.koiyae.gapi.services.ConsumeApi
import br.com.koiyae.gapi.model.Game

fun main() {
    print("Digite um código de jogo para buscar: ")
    val input = readln()

    var myGame: Game? = null

    val searchApi = ConsumeApi()
    val infoGame = searchApi.searchGame(input)

    val result = runCatching {
        myGame = Game(
            infoGame.info.title, infoGame.info.title
        )
    }

    result.onFailure {
        println("Jogo inexistente. Tente outro id.")
    }

    result.onSuccess {
        print("Deseja inserir uma descrição personalizada? s/N ")
        val option = readlnOrNull()
        if (option.equals("s", true)) {
            print("Insira a descrição personalizada para o jogo: ")
            val customDescription = readlnOrNull()
            myGame?.description = customDescription
        } else {
            myGame?.description = myGame?.title
        }
        println(myGame)
    }

    result.onSuccess {
        println("Busca finalizada com sucesso!")
    }
}
