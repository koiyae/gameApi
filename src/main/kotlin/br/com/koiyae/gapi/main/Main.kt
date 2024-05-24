package br.com.koiyae.gapi.main

import br.com.koiyae.gapi.services.ConsumeApi
import br.com.koiyae.gapi.model.Game
import br.com.koiyae.gapi.model.Player
import br.com.koiyae.gapi.utilities.toAge
import java.util.InvalidPropertiesFormatException
import java.util.Scanner

fun main() {
    val input = Scanner(System.`in`)
    val player = Player.createPlayer(input)
    println("Cadastro concluído com sucesso. Dados do player:")
    println(player)

    val resultAge = runCatching {
        println("Idade do player: ${player.birthDate?.toAge()}")
    }

    resultAge.onFailure {
        println("\nFormato de data inválido!\n")
    }

    do {
        print("Digite um código de jogo para buscar: ")
        val convertedInput = readln()
        var myGame: Game? = null

        val searchApi = ConsumeApi()
        val infoGame = searchApi.searchGame(convertedInput)

        val result = runCatching {
            myGame = Game(
                infoGame.info.title, infoGame.info.thumb
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

            player.searchedGames.add(myGame)
        }
        print("Deseja buscar um novo jogo? s/N ")
        val answer = readln()
    } while (answer.equals("s", true))

    println("Jogos buscados:")
    println(player.searchedGames)

    println("\nJogos ordenados por título: ")
    player.searchedGames.sortBy {
        it?.title
    }

    player.searchedGames.forEach {
        println("Título: ${it?.title}")
    }

    val filteredGames = player.searchedGames.filter {
        it?.title?.contains("Batman", true) ?: false
    }

    println("\nJogos filtrados: ")
    println(filteredGames)

    println("Deseja excluir algum jogo da lista original? s/N ")
    val option = input.nextLine()
    if (option.equals("s", true)) {
        println(player.searchedGames)
        println("\nInforme a posição do jogo que quer excluir: ")
        val position = input.nextInt()
        player.searchedGames.removeAt(position)
    }

    println("Busca finalizada com sucesso!")

    println("\nLista atualizada: ")
    println(player.searchedGames)
}
