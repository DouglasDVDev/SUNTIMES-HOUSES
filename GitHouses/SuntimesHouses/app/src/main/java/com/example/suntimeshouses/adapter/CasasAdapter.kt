/*
 * Copyright (c) Created Douglas Díaz on 10/2/23 20:13
 *               *  Copyright Ⓒ 2023. All rights reserved Ⓒ 2023
 *               *  Last modified: 10/2/23 20:13
 *               *
 *               *  Licensed under Douglas Solutions License, Version 2.0 (the "License"); you may not use this file
 *               *  except in compliance with the License. You may obtain a copy of the License at
 *               * Unless required by applicable law or agreed to in writing, software
 *               *   distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *               *   either express or implied. See the License for the specific language governing permissions and
 *               *   limitations under the License.
 */

package com.example.suntimeshouses.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.suntimeshouses.Houses
import com.example.suntimeshouses.R

/* Configuramos el adaptador para que recoja los datos de la lista que se encuentra en
    CasasProvider llamado "listacasas y que lo convierta en un RecycleView*/
class CasasAdapter(
    private val listadecasas:List<Houses>,
    private val clickContact: (Int) -> Unit
    ) : RecyclerView.Adapter<CasasViewHolder>(){

    /*Le pasamos los items provenientes de "item_casas" para que lo envíe al "CasasViewHolder"
    y las pinte en pantalla*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CasasViewHolder(layoutInflater.inflate(R.layout.item_casas, parent, false))

    }
    // Pasa por cada item de la lista y va a llamar a la funcion "render" del "CasasViewHolder"
    override fun onBindViewHolder(holder: CasasViewHolder, position: Int) {
        val item = listadecasas[position]
        holder.render(item, clickContact)
    }

    //Devolvemos el tamaño de la lista
    override fun getItemCount(): Int = listadecasas.size

}