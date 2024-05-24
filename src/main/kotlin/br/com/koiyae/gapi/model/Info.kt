package br.com.koiyae.gapi.model

data class Info(val info: InfoApiShark) {
    override fun toString(): String {
        return info.toString()
    }
}