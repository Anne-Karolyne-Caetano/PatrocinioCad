package com.example.cadastropatrocinadores

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PatrocinadorDao {
    @Insert
    suspend fun inserir(patrocinador: Patrocinador)

    @Query("SELECT * FROM patrocinadores")
    suspend fun listarTodos(): List<Patrocinador>

    @Query("delete from patrocinadores where cnpj = :cnpj")
    suspend fun deletarPorCNPJ(cnpj: String)

    @Query("delete from patrocinadores")
    suspend fun deleteTodos()

}
